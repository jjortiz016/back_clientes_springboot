package com.orbitecsl.springbootbackendapirest.auth;

public class JwtConfig {
    public static final String LLAVE_SECRETA="alguna.clave.secreta.12345678";
    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA326bEKEjFCfeAoB5HdvAFEgJTT1OFrFxgpTNyI6sdb0IS4t8\n" +
            "E5gyCeXPDkWf0vwFFNaPq3ubWZRDe7HtHmuVduVojJux7bxA41moqokEq9fKFZAc\n" +
            "NfhLyfi1HNux6+SRa1EGkUwkEUr/Xk9CPFQ5dh3EaEEi2vTK/Dzm9dAgnTmgLIhn\n" +
            "ajZibds9DWjau3z635Bgzs9WiI1O7LkvLmhfQ3sPo4UfrqmjlSTQDOzlF3V38py2\n" +
            "4LpJuBCEhpjFWIxTgEXfgwMbf1fdP2TEwO/5Uxd1/W0jhHSD8qOJnuW+y5DhQ60y\n" +
            "4qlTvPM31XQZZnc+mWmmhl3F9Tyfwyd2jwjc2wIDAQABAoIBAQC9I25U13NQfqcr\n" +
            "SwI8SmgBQzHMltbh0vgZ6EmDO3O2f3zkJoG0PKQvSMDMfhfbjMfoafFY4twRKrct\n" +
            "oOLvW3c9HNoyHSKz8yaizqU/J5oytgs10bZv4W6W3NjEQx4MaILJa9+5Y9EsamZe\n" +
            "uY2CcHHsESrQbkNJQuwKNqv07miNbkPAn1OwZ6pYy7imUjxSerT98aGH7hScFvSX\n" +
            "TRItaqkV99CQhzbphY+u0llMc3LipeRMci/POudHxYzSGczgJrHN1burWDh1amsp\n" +
            "h4FlouJnnmwDLTFUkVBO6+RkjZChWydkhNpIL1nH87V8ciLNnNEzCY3GkziS036w\n" +
            "+eHNuynRAoGBAPFfi6qFAiA6XorLKCFtdULQr7GXEA/bwXIXgMUdlqOKQXWQuYJP\n" +
            "hIcm8+OyRYNkS2aXFi9DILP8caOTm9Fdnha4hYLyczV2phTb7euXihOy1ssOFr26\n" +
            "7VORhVEx2X8jG3s9NUOqVFL1r0PbL7EP2UmpgrhkN+tW2BgUlCl+Td+/AoGBAOz4\n" +
            "vIWY20eK4mH5/u2cXpCxpQWqr3ujhfgaYhgoOEhGyruZseo1PAL3V2Xd/JOXO/CV\n" +
            "jfLW6HPcGjqu51y4EcAYeoUJoIoGD2g5S13i4Pt/AaB9Wu+VtHY7UMxAjjtmNvPU\n" +
            "HVSv1E5xUbdE8JqB+d9qgRqr4Ag8o9oCuMHp/gnlAoGBAI2V+UxmRy/2bTzHHNw2\n" +
            "4+D0nwryTNawlpHBXaTCzXlTAUDgSyE4WcoZvFilWwdOzrf4Cmhg3Q8XFuXYET/H\n" +
            "xZMYXCDK7W/AEH4N4EshEjqp3jswIYDb5JjvtJ9DOYzNYDJzXgo6IMqlGVEZgqk/\n" +
            "XbtK9sYI65Bd2daOQ7bvQujrAoGAephgZ7lh0ExgvBFquqDIJ5FP73OTX6KEn4t+\n" +
            "rkK4g2PkZFOqQKGTz3EIzRGgJvb5soicrEJUdgQrqaps1YIA5OGFGSiW3nEuLVxe\n" +
            "yk6i2FmnjNDtZ7+KYhRjw5euPbquuUxGdFaWvpJPlUHcPAL8M4JVEmhS+c/tcM8x\n" +
            "qFiW7aUCgYBxyH8aWFPLPpQP7jEZ4k3BZxIIWDDLGNYX9vAdZKWReMtAus3m96sX\n" +
            "cGkYKYf/PAKWY9DCo+/DYZnktu7/EP8ZVq1/bzlYv0D/WDastbB8Ib0hMH0mkTQZ\n" +
            "K2b/pEO+H93uhe//dY2CcYy2LOhQJey81OmUv0tSyQXEA2fUWObdjA==\n" +
            "-----END RSA PRIVATE KEY-----";
    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA326bEKEjFCfeAoB5HdvA\n" +
            "FEgJTT1OFrFxgpTNyI6sdb0IS4t8E5gyCeXPDkWf0vwFFNaPq3ubWZRDe7HtHmuV\n" +
            "duVojJux7bxA41moqokEq9fKFZAcNfhLyfi1HNux6+SRa1EGkUwkEUr/Xk9CPFQ5\n" +
            "dh3EaEEi2vTK/Dzm9dAgnTmgLIhnajZibds9DWjau3z635Bgzs9WiI1O7LkvLmhf\n" +
            "Q3sPo4UfrqmjlSTQDOzlF3V38py24LpJuBCEhpjFWIxTgEXfgwMbf1fdP2TEwO/5\n" +
            "Uxd1/W0jhHSD8qOJnuW+y5DhQ60y4qlTvPM31XQZZnc+mWmmhl3F9Tyfwyd2jwjc\n" +
            "2wIDAQAB\n" +
            "-----END PUBLIC KEY-----";

}
