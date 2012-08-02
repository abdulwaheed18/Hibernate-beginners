package com.hibernate.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import static javax.persistence.GenerationType.IDENTITY;


/**
 * 
 * @author abdul
 *
 */
@Entity
@Table(name = "HOST1", uniqueConstraints = { @UniqueConstraint(columnNames = {
        "ADDRESS", "PORT" }) })
public class Host {
    private static final long serialVersionUID = 1L;

    @Column(name = "ADDRESS")
    private String address = null;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "host_id", unique = true, nullable = false)
    private Long id = null;

    @Column(name = "MAX_TENANTS")
    private int maxTenants = 0;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "PORT")
    private int port;

   
    /**
     * Returns address of the host. This can be either IP address or host name.
     * 
     * @return host address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the host description.
     * @return host description
     */
    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    /**
     * Returns the host location. 
     * @return host location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the maxTenants
     */
    public int getMaxTenants() {
        return maxTenants;
    }

    /**
     * Returns the management port. This is the port where the Bootstrap Agent
     * must be listening.
     * 
     * @return management port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the address of this host.
     * 
     * @see #getAddress()
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the host description.
     * 
     * @see #getDescription()
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the host location.
     * 
     * @see #getLocation()
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @param maxTenants the maxTenants to set
     */
    public void setMaxTenants(int maxTenants) {
        this.maxTenants = maxTenants;
    }

    /**
     * Sets the management port.
     * 
     * @see #getPort()
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }
   
}
