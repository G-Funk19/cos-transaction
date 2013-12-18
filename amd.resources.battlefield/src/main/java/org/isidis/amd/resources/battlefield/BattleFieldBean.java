package org.isidis.amd.resources.battlefield;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.isidis.amd.cos.transactions.Transaction;
import org.isidis.amd.cos.transactions.TransactionException;
import org.isidis.amd.resources.common.BattleField;
import org.isidis.amd.resources.common.Unit;

public class BattleFieldBean extends UnicastRemoteObject implements BattleField 
{
	private static final long serialVersionUID = -6622439355462427448L;
	private List<Unit> register;
	private Transaction currentTransaction;
	
	public BattleFieldBean() throws RemoteException 
	{
		super();
		register = new ArrayList<Unit>();
	}
	
	public Unit deploy(Unit pUnit) throws RemoteException 
	{
		Unit novice = new Warrior(pUnit);
		register.add(novice);
		System.out.println("Register updated");
		return novice;
	}
	
	@Override
	public void prepare() throws RemoteException 
	{
		System.out.println("Base resource is now prepared by the transaction "+ currentTransaction.getId());
	}
	
	@Override
	public void commit() throws TransactionException, RemoteException 
	{
		System.out.println("Base resource is now committed by the transaction "+ currentTransaction.getId());
	}
	
	@Override
	public void rollback() throws TransactionException, RemoteException 
	{
		System.out.println("Base resource is now rollback by the transaction "+ currentTransaction.getId());
	}

	@Override
	public void activeTransaction(Transaction pTransaction) throws RemoteException
	{
		currentTransaction = pTransaction;
	}
}