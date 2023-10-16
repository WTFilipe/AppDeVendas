package com.filipeoliveira.appdevendas.data.local

import com.filipeoliveira.data.local.model.OrderDB
import com.filipeoliveira.data.local.model.OrderItemDB
import com.filipeoliveira.data.local.model.OrderWithItemsDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SalesLocalDataImpl @Inject constructor (
    private val appDatabase: AppDatabase
) : SalesLocalData{
    override suspend fun getOrderWithItemsList(): Flow<List<OrderWithItemsDB>> = appDatabase.getOrderWithItemsDao().getOrdersWithItemsList()
    override suspend fun getCart(): Flow<List<OrderWithItemsDB>> = appDatabase.getOrderWithItemsDao().getCart()
    override suspend fun addToCart(item: OrderItemDB) {
        if (item.quantityOfItems > 0){
            appDatabase.getOrderWithItemsDao().insertOrderItem(item)
            appDatabase.getOrderWithItemsDao().insertOrder(OrderDB(orderId = OrderDB.CART_ORDER_ID, isStillInCart = true))
        } else {
            appDatabase.getOrderWithItemsDao().deleteCartItem(item.sku)
        }
    }

    override suspend fun finishPurchase(orderWithItems: OrderWithItemsDB) {
        val orderID = System.currentTimeMillis()
        for (item in orderWithItems.items){
            appDatabase.getOrderWithItemsDao().insertOrderItem(item.copy(orderId = orderID))
        }
        appDatabase.getOrderWithItemsDao().insertOrder(OrderDB(orderId = orderID, isStillInCart = false))
        appDatabase.getOrderWithItemsDao().deleteCart()
    }
}