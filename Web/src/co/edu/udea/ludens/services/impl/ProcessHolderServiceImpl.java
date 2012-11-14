package co.edu.udea.ludens.services.impl;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.udea.ludens.services.GameProcess;
import co.edu.udea.ludens.services.Process;
import co.edu.udea.ludens.services.ProcessHolderService;

@Service("processHolderService")
@Scope("singleton")
public class ProcessHolderServiceImpl implements ProcessHolderService {

	private HashMap<String, Process> gameProcesses = new HashMap<String, Process>();

	@Override
	@SuppressWarnings("rawtypes")
	public Process findProcessById(Class entityClass, Object primaryKey) {
		if (entityClass.getName().equals(GameProcess.class))
			gameProcesses.get(primaryKey);

		return null;
	}

	@Override
	public void putProcess(Object primaryKey, Process process) {
		if (process.getClass().getName().equals(GameProcess.class))
			gameProcesses.put(primaryKey.toString(), process);

	}

	@Override
	public Process removeProcess(Object primaryKey) {

		return (this.gameProcesses.remove(primaryKey));
	}
}
