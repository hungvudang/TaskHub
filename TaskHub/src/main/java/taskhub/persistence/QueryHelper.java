package taskhub.persistence;

import java.util.List;

import javax.enterprise.inject.Vetoed;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;

import taskhub.cdi.EmLocator;
import taskhub.persistence.entity.Abstract_entity;

@Vetoed
public class QueryHelper <T extends Abstract_entity , Y> {

	public CriteriaBuilder cb;
	public Root<T> root;
	public CriteriaQuery<Y> query;
	
	public QueryHelper(final CriteriaBuilder cb, final CriteriaQuery<Y> query, final Root<T> root) {
		this.cb = cb;
		this.query = query;
		this.root = root;
	}
	
	public static <T extends Abstract_entity> QueryHelper<T, T> create(final Class<T> clazz) {
		final CriteriaBuilder cb = EmLocator.getEm().getCriteriaBuilder();
		final CriteriaQuery<T> query = cb.createQuery(clazz);
		final Root<T> root = query.from(clazz);
		query.select(root);
		return new QueryHelper<T, T>(cb, query, root);
	}
	
	
	public static <Y, T extends Abstract_entity> QueryHelper<T, Y> create(final Class<T> clazz, final SingularAttribute<? super T, Y> selectionSingularAttribute, final Class<Y> selectionClass) {
		final CriteriaBuilder cb = EmLocator.getEm().getCriteriaBuilder();
		final CriteriaQuery<Y> query = cb.createQuery(selectionClass);
		final Root<T> root = query.from(clazz);
		query.select(root.get(selectionSingularAttribute));

		return new QueryHelper<T, Y>(cb, query, root);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Long countOnlyIfNeeded(final CriteriaQuery query) {
		final Selection selection = query.getSelection();
		final List<Order> orderList = query.getOrderList();
		query.orderBy();
		
		final CriteriaBuilder cb = EmLocator.getEm().getCriteriaBuilder();
		query.select(cb.count(cb.literal(1)));
		
		final TypedQuery<Long> tq = EmLocator.getEm().createQuery(query);
		
		query.select(selection);
		query.orderBy(orderList);
		
		return tq.getSingleResult();
	}
}
