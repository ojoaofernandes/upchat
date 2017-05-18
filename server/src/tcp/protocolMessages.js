'use strict';

const protocolMessageSeparator = '\\r\\n\\r\\n';

const parseHeader = function (str) {
    const arr = str.trim().split(' ');

    const header = {
        raw: str,
        protocol: arr.shift(),
        params: arr
    };

    return header;
}

const splitHeaderFromBody = function (str) {
    const arr = str.split(protocolMessageSeparator);
    const header = parseHeader(arr.shift());
    const body   = arr.shift();

    return {header, body};
};

const newProtocolMessage = function ({sender, header, body}) {
    return {sender, header, body};
}

const parse = function (buffer, sender) {
    const {header, body} = splitHeaderFromBody(buffer.toString());
    const message = newProtocolMessage({sender, header, body});
    message.raw = buffer.toString();

    return message;
};

const inspect = function (message) {
    console.log('\n');
    console.log(`New message ${new Date()}`);
    console.log(`\tsender:\t${message.sender.id}`);
    console.log(`\traw:\t${message.raw}`);
    console.log(`\theader:\t${message.header.raw}`);
    console.log(`\tbody:\t${message.body}`);
    console.log('\n');
}

const protocolMessages = {
    parse, inspect
};

module.exports = protocolMessages;