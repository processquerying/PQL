package org.processmining.contexts.uitopia;

import org.processmining.framework.connections.ConnectionManager;
import org.processmining.framework.plugin.GlobalContext;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.PluginContextID;
import org.processmining.framework.plugin.PluginDescriptor;
import org.processmining.framework.plugin.PluginManager;
import org.processmining.framework.plugin.PluginParameterBinding;
import org.processmining.framework.providedobjects.ProvidedObjectManager;

public class DummyGlobalContext implements GlobalContext {

	@Override
	public PluginManager getPluginManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProvidedObjectManager getProvidedObjectManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConnectionManager getConnectionManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PluginContextID createNewPluginContextID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invokePlugin(PluginDescriptor plugin, int index,
			Object... objects) {
		// TODO Auto-generated method stub

	}

	@Override
	public void invokeBinding(PluginParameterBinding binding, Object... objects) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<? extends PluginContext> getPluginContextType() {
		// TODO Auto-generated method stub
		return null;
	}

}
