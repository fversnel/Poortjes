package org.frankversnel.nl.poortjes.component;

import org.frankversnel.nl.poortjes.GameObject;

public class ComponentNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8863053240768908484L;

	public <T extends Component>  ComponentNotFoundException(Class<T> componentType, 
			GameObject gameObject) {
		super("Component of type " + componentType + 
				" could not be found for " + gameObject);
	}

}
