package taskhub.cdi;

import taskhub.cdi.helper.CDIHelper;

public class Instances {
	
	public static <T> T get(final Class<T> type) {
		return CDIHelper.getContextualReference(type);
	}
}
