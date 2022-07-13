package com.example.customerstest.models


data class CustomerList(val customers: ArrayList<Customer>)
data class Customer(
    val email: String,
    val id: String,
    val name: String,
    val phone: String,
    val profileImage: String,
    val profileLink: String,
    val status: String
){

}