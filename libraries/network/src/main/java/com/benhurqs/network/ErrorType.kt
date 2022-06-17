package com.benhurqs.network

enum class ErrorType(var value: String) {
    EMPTY("Não encontramos nenhum perfil."),
    NETWORK("Verifique sua internet e tente novamente."),
    SERVER("Infelizmente ocorreu um problema no nosso sistema."),
    WEBVIEW("Infelizmente não conseguimos carregar esse perfil."),
    DEFAULT("Infelizmente não conseguimos carregar os perfis.");



}