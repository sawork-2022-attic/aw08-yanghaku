# aw08

change log:

1. copy from aw07
2. 修改pos-delivery, 维护每个订单的状态, 并且可以通过rest返回订单的状态信息
3. 修改pos-gateway与pos-delivery整合, 通过访问gateway的 ```/deliveryIntegration/{orderId}``` 来获取delivery信息.



使用spring integration优势:

1. delivery可以与micropos解耦合, 可以随时替换成其他delivery服务.
2. 可以利用任意消息协议(如http),  还可以利用其他消息协议加快数据传输.



Run the project with `mvn spring-boot:run` and send request to `http://localhost:8080/check`. You should see an reponses in json format like the following.

```json
{
    "icon_url": "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
    "id": "kswv7NIaTCaIIErlBzODaA",
    "url": "https://api.chucknorris.io/jokes/kswv7NIaTCaIIErlBzODaA",
    "value": "Chuck Norris's shadow weighs 250 pounds and can kick your ass ."
}
```

Try to understand the provided code which demonstrates spring integration between a spring boot application with an externel http service (https://api.chucknorris.io/jokes/random).

Please implement delivery as an standalone service (just like the random joke service). Refer the sample code to integrate your Micropos system with delivery service so that user can check delivery status on Miropos which actually forwards user request to delivery service on demand.

![](Micropos.svg)

Consider the advantage by doing so and write it down in your readme file.