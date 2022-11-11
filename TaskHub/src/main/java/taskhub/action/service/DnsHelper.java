package taskhub.action.service;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.LockModeType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import taskhub.cdi.EmLocator;
import taskhub.persistence.entity.Mt_document_numbering_scheme;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class DnsHelper implements Serializable {
	
	public String next(@NotNull final String numbering_scheme_key) {
		if (!!!StringUtils.isBlank(numbering_scheme_key)) {
			final Mt_document_numbering_scheme dns = EmLocator.getEm().find(Mt_document_numbering_scheme.class, numbering_scheme_key, LockModeType.PESSIMISTIC_WRITE);
			if (dns != null) {
				dns.setLast_no(dns.getLast_no() + 1);
				return dns.next();
			}
		}
		return null;
	}
	
}
