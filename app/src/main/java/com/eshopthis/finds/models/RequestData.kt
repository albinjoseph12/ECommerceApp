package com.eshopthis.finds.models

import com.eshopthis.finds.data.GetProduct

data class RequestData(
    val success : String,
    val data : List<GetProduct>
)
