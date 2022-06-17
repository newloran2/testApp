package com.benhurqs.network.model

import java.io.Serializable

class CustomerListResponse: Serializable {
    var customers: List<CustomerResponse?>? = null
}