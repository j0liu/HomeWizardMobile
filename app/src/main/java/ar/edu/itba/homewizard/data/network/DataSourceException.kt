package ar.edu.itba.homewizard.data.network

class DataSourceException(
    code: Int,
    message: String,
    details: List<String>?
) : Exception(message)