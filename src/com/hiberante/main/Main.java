/**
 * 
 */
package com.hiberante.main;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.pojo.Host;
import com.hibernate.util.HibernateUtil;

/**
 * 
 * This is a simple hibernate tutorial where I have done few hibernate tasks like adding data to the database,reading , counting 
 *  and deleting from the database.
 *  
 * @author abdul
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Main main = new Main();

        //address,port,location
        main.addHost("localhost1",1111,"A");
        main.addHost("localhost2",1111,"B");
        main.addHost("localhost3",1111,"C");
        main.addHost("localhost4",1111,"D");
        main.addHost("localhost5",1111,"E");
        main.getId("C"); // will return the Host Id of Location "C";
        main.getMaxId(); 
        main.getCount(4);
       
        Host host1 = new Host();
        host1.setId((long)4);
        main.deleteHost(host1);
    }

        
    /**
     * Returns the id of the location
     * 
     * @param location
     */
    private void getId(String location) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            @SuppressWarnings("unchecked")
            List<Host> hosts = session.createQuery("from Host as h where h.location = :location")
            .setString( "location", location )
            .list();
            for (Iterator<Host> iter = hosts.iterator(); iter.hasNext();) {
                Host host = iter.next();
                System.out.println("hostId of location " + host.getLocation() +" is " + host.getId());
            }
            trns.commit();
        } catch (RuntimeException e) {
            if(trns != null){
                trns.rollback();
            }
            e.printStackTrace();
        } finally{
            session.flush();
            session.close();
        } 

    }

    /**
     * Gets the id and count whethere there is any duplicate entry.
     * I know its does not make any sense but this function is just for using aggregate function
     * 
     * @param id
     */
    private void getCount(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String countQuery = "select count(id) from "+ Host.class.getName() +" where id=" + id;
            Query query = session.createQuery(countQuery);
            @SuppressWarnings("unchecked")
            List<Host> list = query.list();
            System.out.println("Count: " + list.get(0)); 
            session.close();

        } catch(RuntimeException e ) {
            System.out.println("Exception: " + e.getMessage());

        }
    }

    /**
     * Gets the max value from the ID
     */
    private void getMaxId() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String SQL_QUERY = "select max(id) from Host";
            Query query = session.createQuery(SQL_QUERY);
            long count = 0;
            List list = query.list();
            if(list.get(0) == null || list.isEmpty()) {
                count = 1;
            } else {
                count = (Long) list.get(0);
            }
            System.out.println("Maximum Id: " + count); 
            session.close();
        }catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    /**
     * Add the host object into the database
     * 
     * @param address
     * @param port
     * @param location
     */
    private void addHost(String address,int port, String location) {

        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Host host = new Host();
            host.setAddress(address);
            host.setLocation(location);
            host.setPort(port);
            session.save(host);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if(trns != null){
                trns.rollback();
            }
            e.printStackTrace();
        } finally{
            session.flush();
            session.close();
        } 
    }
    
    /**
     * Delete the host data from the database
     * @param host
     */
    private void deleteHost(Host host) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.delete(host);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if(trns != null){
                trns.rollback();
            }
            e.printStackTrace();
        } finally{
            session.flush();
            session.close();
        }
    } 
}
