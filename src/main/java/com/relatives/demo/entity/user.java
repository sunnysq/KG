package com.relatives.demo.entity;

public class user {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.usename
     *
     * @mbggenerated
     */
    private String usename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.usename
     *
     * @return the value of user.usename
     *
     * @mbggenerated
     */
    public String getUsename() {
        return usename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.usename
     *
     * @param usename the value for user.usename
     *
     * @mbggenerated
     */
    public void setUsename(String usename) {
        this.usename = usename == null ? null : usename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}