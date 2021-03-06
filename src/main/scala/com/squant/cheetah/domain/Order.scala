package com.squant.cheetah.domain

import java.time.LocalDateTime

sealed trait OrderStyle {
  def price(): Double = ???
}

//市价单: 不论价格, 直接下单, 直到交易全部完成
case class MarketOrderStyle() extends OrderStyle

//限价单: 指定一个价格, 买入时不能高于它, 卖出时不能低于它, 如果不满足, 则等待满足后再交易
case class LimitOrderStyle(limitPrice: Double) extends OrderStyle {
  override def price() = limitPrice
}

case class Order(code: String, amount: Int, style: OrderStyle, direction: Direction, date: LocalDateTime) {
  var state: OrderState = OPEN
  var price = style.price
  var volume = amount * style.price
}
