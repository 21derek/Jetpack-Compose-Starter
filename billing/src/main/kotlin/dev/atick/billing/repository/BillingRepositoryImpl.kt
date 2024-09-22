package dev.atick.billing.repository

import android.app.Activity
import dev.atick.billing.data.BillingDataSource
import dev.atick.billing.models.OneTimePurchase
import dev.atick.billing.models.Product
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BillingRepositoryImpl @Inject constructor(
    private val billingDataSource: BillingDataSource,
) : BillingRepository {
    override val products: StateFlow<List<Product>>
        get() = billingDataSource.products

    override val purchases: SharedFlow<List<OneTimePurchase>>
        get() = billingDataSource.purchases

    override suspend fun initializeBilling(): Result<Unit> {
        return runCatching {
            billingDataSource.initializeBilling()
        }
    }

    override suspend fun purchaseProduct(activity: Activity, product: Product): Result<Unit> {
        return runCatching {
            billingDataSource.purchaseProduct(activity, product)
        }
    }

    override suspend fun verifyAndAcknowledgePurchases(purchases: List<OneTimePurchase>): Result<Unit> {
        return runCatching {
            billingDataSource.verifyAndAcknowledgePurchases(purchases)
        }
    }
}