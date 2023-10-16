package com.filipeoliveira.data.local

import com.filipeoliveira.data.local.model.CartItemDB
import com.filipeoliveira.data.local.model.OrderDB
import com.filipeoliveira.data.local.model.OrderWithItemsDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SalesLocalDataImpl @Inject constructor (
    private val appDatabase: AppDatabase
) : SalesLocalData {
    override suspend fun getOrderWithItemsList(): Flow<List<OrderWithItemsDB>> = appDatabase.getOrderWithItemsDao().getOrdersWithItemsList()
    override suspend fun getCart(): Flow<List<CartItemDB>> = appDatabase.getOrderWithItemsDao().getCart()
    override suspend fun addToCart(cartItemDB: CartItemDB) {
        if (cartItemDB.quantityOfItems > 0){
            appDatabase.getOrderWithItemsDao().insertCartItem(cartItemDB)
        } else {
            appDatabase.getOrderWithItemsDao().deleteCartItem(cartItemDB.sku)
        }
    }

    override suspend fun finishPurchase(orderWithItems: OrderWithItemsDB) {
        val orderID = System.currentTimeMillis()
        for (item in orderWithItems.items){
            appDatabase.getOrderWithItemsDao().insertOrderItem(item.copy(orderId = orderID))
        }
        appDatabase.getOrderWithItemsDao().insertOrder(OrderDB(orderId = orderID))
        appDatabase.getOrderWithItemsDao().deleteCart()
    }
}