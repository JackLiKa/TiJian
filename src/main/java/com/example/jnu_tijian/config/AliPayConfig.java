package com.example.jnu_tijian.config;

import com.alipay.easysdk.kernel.Config;
import org.springframework.stereotype.Component;

@Component
public class AliPayConfig {
    private static String gatewayHost = "openapi-sandbox.dl.alipaydev.com";
    private static String appId = "9021000150658726";
    private static String merchantPrivateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC2FAOnXVkXDxpxJUsMfqPrpFudMJ23nXdBwYNglURIR9mBPt7DB2Qhkd/D/Fwt+bRyAETR+RyWvz5L6iYkSKnL7lc/EvLbd9k0yBv3NRUqUL7sa3feZOGBrUU3r4i8+iVbxiDC9KJVagjHd8A5s5AFek7CrF9yFSCnzFo41YDEjWZhM/Nv6npJjN/1gSgQcnQIFHdYG9jO45vo4nJwUYD6VxXPDswawjdCXju29ub3lu9EVrQL0KZrdk+s4T448IEh1Az5hqTtsOgopugZrqpd/gTICR/5pG01aAkladumgTFqEi3ei5bXbHaK28bJAkdPCVS1YRVfgnurY/+q+BT5AgMBAAECggEBAKiP3tISmWe/k7sry07F7vs4LZhYaaqTHNTDUd0YUx6XZcVBbvke3pQ1d/L4rl3hziu8E/pPpSfp5MJjAuyUWJ1zCVJbOHn1eiBjIYC6o92I4zf8RfydaCelQW9MEkrhSbSst6fE0oRApeAD/PFuSubHVCLlcXcsGA9AIEZ9YbiTnhSQHfWh14f//mG9n091bcnqh0yk/10zXKrdd6Rmzq7XOTDYTxRplxnFeWjP9vkkNybgKTsr1r/A84u6jyzRQtj2WEGbv8wgUx6pfIq3+CccgZGh+wj5UcmRY/O+1XNaTsdZ0a3nisihro9/+Asl2SrPV6RPXdVhG+E+o31AEAECgYEA6pC8I+C1HLNIBTM19E0NYxF637F6KrfRlURExhCAycF/ZKRr83SWB7UbNcT9srMnzjiAqeh044r9l1vLtacPgNoAIF1ilcJfRUDOQsaB5itjzS7IhLkikC46MfA2Z97Co1yY1LikKJ5BBIwt7UIDZ3V/e2Y5spgjWbe2LGf0Jg0CgYEAxrdt4Ecbp/Eshhfp+4oO7zX1EGWicILfjc5/SHNmX6MUSFsrfOqGMjzwTzON3QFZUmrK5mCRsa4kylsms+FnjRB/dqKsJ5MmTDn0E0gm0DfqhKLS6naQmlDAjTHQTte+0OGOTlBUSdWbR6kmQbd6IWZUXsMbfrVIguMYM68m+50CgYEA5VJrHYhqiIyeNLdEYHuUQu6r+lZA4dKkjBIaSqmHv7f1HCfDgljQ9OgSmhblEPgmSm7jGzmwTWVtV7TfC9EwTwDSbtHduBw3mDUrcg0woh55i/fZAikdqWgIbRozlokqMRwuLon1s+BEIHYL0JFhUpyE7debQQeMSAJbkD5xa2ECgYEArO8xFf+MF+mQ/9mL7TZGrPLeJLjuVs/uUXCl4J1dOFmp6xY8kGdhBhN2ZtbM3Mqxu0Ho9fiQknqk1d3hnqXJAkCbTNlgzMgIikndGXKvJIGk/Gze9ODeEw23pKj3ScYD77B7sbfCwsa8be7MtmBATxTI0/cj1G9bfVy40THa0RkCgYBQ8H+NWz/wERyt/8/CdUP35uz2dQPBPOAHKwDW1b6rJBX8XVwnVLiP1QlQvbZFWl9PO0uYrKZL5L9Jk/PxLA49ddR9Y5jbWkCZ9PwSFHi+4yFGfKd8tqYYdRKKpxStW9Bm3hOoma5q4u4JgHTUejZiXfGLDLVS9bhY6i5rk1dsjg==";
    private static String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoOXWdEoyB4sP8PtTO9JgcJfVtgLPjwKWZr7mhLxo1vhTm1OGeJJQ87/5WcXiRz4mK+lufCLickdM3R3bAs9vhtv6v8nW5vV1LV4siQ8ar6vmMC0PCKjK1XQ26SGVDKyW7EoCIuCLG5E6Lt3OjzrBn14aOtk8+7MN3ZMKqokIFIzTrZKqY65NEkyppeSM5qQgjuBQGNZTfPSV/bEscFY0Mvz17TeQ1S5kTF9duU/j6xAOwbXdwZlfr9POlNhNVtxWUzmNmMreFESYNixUS5RPZwXZFkeG7o9RDpWXErwTgWy3cx49eUzsLrATejkt3lpWmeJpCIbpygpAXgPiOyOmXQIDAQAB";
    private static String notifyUrl = "http://c88c65cd.natappfree.cc/api/alipay/notify";

    public Config init() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = this.getGatewayHost();
        config.signType = "RSA2";
        config.appId = this.getAppId();
        config.merchantPrivateKey = this.getMerchantPrivateKey();
        config.alipayPublicKey = this.getAlipayPublicKey();
        config.notifyUrl = this.getNotifyUrl();

        System.out.println("=======支付宝SDK初始化成功=======");
        System.out.println("AppID: " + config.appId);
        System.out.println("Gateway: " + config.gatewayHost);
        System.out.println("Notify URL: " + config.notifyUrl);

        return config;
    }


    public String getGatewayHost() {
        return gatewayHost;
    }

    public void setGatewayHost(String gatewayHost) {
        this.gatewayHost = gatewayHost;
    }

    public String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public void setMerchantPrivateKey(String merchantPrivateKey) {
        this.merchantPrivateKey = merchantPrivateKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}