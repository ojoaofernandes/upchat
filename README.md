# UP Chat

## Introduction

UPChat is a simple chat service that serves as an alternative to instant messaging services already known. It allows authentication with third-party APIs and messages exchange with your friends in the respective API used.

This is a simple implementation developed with NodeJS (server) and Java (client) for Distributed Systems discipline at the Faculty of Engineering of the University of Porto.

## Service

The service uses a `client-server model` mainly built over a TCP connection, only requires that the client is able to perform an HTTP request to authenticate.

A `client` is a program that establishes a connection to a server for the purpose of to authenticate using third-party API, retrieve personal information, friends list and chat with another client. A `server` is a program that accepts connections in order to handle with clients' personal information and chats.

The chat service herein described is designed to hide the details of implementation by presenting a standard message exchange between the client and the server. In this way, each client or server is free to choose its own technology stack.

## Protocol specification

The protocol used by the chat service comprises some smaller subprotocols which are used for specific tasks: authentication, account operations and chatting. In addiction, it is composed of a single line `header` followed by an optional multiple line `body`.

**Message format:**
```
HEADER CRLF CRLF BODY
```

### Authentication Protocol

Description.

#### LOGIN_REQUEST

* Sender:   Client
* Receiver: TCP Server

**Message format:**
```
HEADER
LOGIN_REQUEST <third-party api name>

BODY
-
```

#### LOGIN_DESTINATION

* Sender:   TCP Server
* Receiver: Client

**Message format:**
```
HEADER
LOGIN_DESTINATION <third-party api url>

BODY
-
```

#### LOGIN_SUCCESS

* Sender:   HTTP Server
* Receiver: TCP Server

**Message format:**
```
HEADER
LOGIN_SUCCESS <some client identifier>

BODY
<client informations>
```

#### LOGIN_ERROR

* Sender:   HTTP Server
* Receiver: TCP Server

**Message format:**
```
HEADER
LOGIN_ERROR <some client identifier>

BODY
-
```

#### WELCOME

* Sender:   TCP Server
* Receiver: Client

**Message format:**
```
HEADER
WELCOME

BODY
<client informations>
```

#### LOGOUT

* Sender:   Client
* Receiver: TCP Server

**Message format:**
```
HEADER
LOGOUT

BODY
-
```

### Account Operations Protocol

Description.

### Chatting Protocol

Description.

#### MESSAGE_TO

* Sender:   Client
* Receiver: TCP Server

**Message format:**
```
HEADER
MESSAGE_TO <friend`s username>

BODY
<message content>
```

#### MESSAGE_FROM

* Sender:   TCP Server
* Receiver: Client

**Message format:**
```
HEADER
MESSAGE_FROM <friend`s username>

BODY
<message content>
```

## Authors

* **João Fernandes** - [github](https://github.com/ojoaofernandes)
* **Joel Carneiro** - [github](https://github.com/jolasman)
* **Rúben Marques** - [github](https://github.com/zabrn)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
