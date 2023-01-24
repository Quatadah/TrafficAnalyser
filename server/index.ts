const express = require("express");
const app = express();
const PORT = process.env.PORT || 4000;
const dotenv = require("dotenv");
const hbase = require("hbase");
const assert = require("assert");
dotenv.config();

app.get("/", (req, res) => {
    res.send({
        message: "Hello World",
    });
});

const krb5 = require("krb5");
const client = hbase({
    host: "lsd-prod-namenode-0.lsd.novalocal",
    protocol: "https",
    port: 8080,
    krb5: {
        service_principal: "HTTP/lsd-prod-namenode-0.lsd.novalocal", //il faut preciser le service principal,il n'est pas bien construit dans le code du package hbase si on ne le donne pas
        principal: "VOTRE_USERNAME@LSD.NOVALOCAL", //,
    },
});

client.table("[VOTRE_USERNAME]:testtable").schema(function (error, schema) {
    console.info(schema);
    console.info(error);
});

app.listen(PORT, () => {
    console.log(`Server started on port http://localhost:${PORT}`);
});
