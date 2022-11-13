package taskhub.persistence.entity.mt;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import taskhub.persistence.entity.Abstract_entity;
import taskhub.persistence.entity._Pk;

@SuppressWarnings("serial")
@Entity
public class Mt_document_numbering_scheme extends Abstract_entity {

	@EmbeddedId
	private Pk pk;

	@Column(updatable = false, insertable = false)
	private String numbering_scheme_key;

	@Column(length = 50)
	@Size(max = 50)
	private String prefix;

	@Column(length = 50)
	@Size(max = 50)
	private String suffix;

	@Column
	private Integer last_no;

	@Column
	private Integer length_to_fill;

	protected Mt_document_numbering_scheme() {
		super();
	}

	public Mt_document_numbering_scheme(@Size(max = 50) String numbering_scheme_key) {
		this.pk = new Pk(numbering_scheme_key);
		this.numbering_scheme_key = numbering_scheme_key;
	}

	public String getNumbering_scheme_key() {
		return this.pk.getNumbering_scheme_key();
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getLast_no() {
		return last_no;
	}

	public void setLast_no(int last_no) {
		this.last_no = last_no;
	}

	public int getLength_to_fill() {
		return length_to_fill;
	}

	public void setLength_to_fill(int length_to_fill) {
		this.length_to_fill = length_to_fill;
	}

	public String next() {
		final String number = Integer.toString(this.getLast_no());
		final String prefix = StringUtils.trimToEmpty(this.getPrefix());
		final String suffix = StringUtils.trimToEmpty(this.getSuffix());

		final int padLength = this.getLength_to_fill() - number.length() - prefix.length() - suffix.length();
		if (padLength < 0) {
			return null; // unable to generate number. front end code should check for this and notify
							// user appropriately
		}

		final char fillChar = '0';
		final String pad = StringUtils.repeat(fillChar, padLength);

		return prefix + pad + number + suffix;
	}

	@Override
	public _Pk getPk() {
		return this.pk;
	}
	
	@Embeddable
	public static class Pk extends _Pk {

		@Column(length = 50, insertable = false, updatable = false)
		@Size(max = 50)
		private String numbering_scheme_key;

		public Pk() {
		}
		
		public Pk(@Size(max = 50) String numbering_scheme_key) {
			super();
			this.numbering_scheme_key = numbering_scheme_key;
		}

		public String getNumbering_scheme_key() {
			return this.numbering_scheme_key;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.numbering_scheme_key == null) ? 0 : this.numbering_scheme_key.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pk other = (Pk) obj;
			if (this.numbering_scheme_key == null) {
				if (other.numbering_scheme_key != null)
					return false;
			} else if (!this.numbering_scheme_key.equals(other.numbering_scheme_key))
				return false;
			return true;
		}

	}

}
