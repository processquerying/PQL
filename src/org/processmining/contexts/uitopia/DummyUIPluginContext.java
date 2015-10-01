package org.processmining.contexts.uitopia;

import java.util.Collection;

import org.processmining.framework.connections.Connection;
import org.processmining.framework.connections.ConnectionCannotBeObtained;
import org.processmining.framework.connections.ConnectionID;
import org.processmining.framework.connections.ConnectionManager;
import org.processmining.framework.plugin.GlobalContext;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.events.ConnectionObjectListener.ListenerList;


public class DummyUIPluginContext extends UIPluginContext {

	private ConnectionManager cm;

	public DummyUIPluginContext(GlobalContext context, String label) {
		super(context, label);
		
		this.progress = new DummyProgress();
		this.cm = new ConnectionManager() {
			
			@Override
			public <T extends Connection> T getFirstConnection(Class<T> connectionType,
					PluginContext context, Object... objects)
					throws ConnectionCannotBeObtained {
				return null;
			}
			
			@Override
			public <T extends Connection> Collection<T> getConnections(
					Class<T> connectionType, PluginContext context, Object... objects)
					throws ConnectionCannotBeObtained {
				return null;
			}
			
			@Override
			public ListenerList getConnectionListeners() {
				return null;
			}
			
			@Override
			public Collection<ConnectionID> getConnectionIDs() {
				return null;
			}
			
			@Override
			public Connection getConnection(ConnectionID id)
					throws ConnectionCannotBeObtained {
				throw new ConnectionCannotBeObtained("", null);
			}
			
			@Override
			public <T extends Connection> T addConnection(T connection) {
				return null;
			}

			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			public void setEnabled(boolean isEnabled) {
				// TODO Auto-generated method stub
				
			}

			public void clear() {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public <T extends Connection> T addConnection(T c) {
		return null;
	}

	@Override
	public <T, C extends Connection> Collection<T> tryToFindOrConstructAllObjects(Class<T> type,
			Class<C> connectionType, String role, Object... input) throws ConnectionCannotBeObtained {
		throw new ConnectionCannotBeObtained("", null);
	}
	
	@Override
	public ConnectionManager getConnectionManager() {
		return cm;
	}
}
