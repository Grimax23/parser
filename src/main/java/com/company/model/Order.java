package com.company.model;

/**
 * Входные данные:
 * CSV файл.
 * Назначение столбцов:
 * Идентификатор ордера, сумма, валюта, комментарий
 * <p>
 * Пример записи:
 * 1,100,USD,оплата заказа
 * 2,123,EUR,оплата заказа
 * <p>
 * Примечание: все столбцы обязательны
 * <p>
 * JSON файл.
 * Пример записи:
 * {“orderId”:3,”amount”:1.23,”currency”:”USD”,”comment”:”оплата заказа”}
 * {“orderId”:4,”amount”:1.24,”currency”:”EUR”,”comment”:”оплата заказа”}
 * <p>
 * Примечание: все поля обязательны
 */
public class Order {
    private String orderId;
    private String amount;
    private String currency;
    private String comment;

    public Order() {
    }

    public Order(String orderId, String amount, String currency, String comment) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) return false;
        if (amount != null ? !amount.equals(order.amount) : order.amount != null) return false;
        if (currency != null ? !currency.equals(order.currency) : order.currency != null) return false;
        return comment != null ? comment.equals(order.comment) : order.comment == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}

