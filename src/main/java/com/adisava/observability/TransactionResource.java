package com.adisava.observability;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tx")
public class TransactionResource {

    @Metric(name = "transaction-evolution")
    Histogram transactionHistogram;

    private long highestTransaction = 0;

    @Counted(
            name = "number-of-transactions",
            displayName = "Transactions",
            description = "How many transactions have been processed")
    @Metered(
            name = "transactions",
            unit = MetricUnits.SECONDS,
            description = "Rate of transactions"
    )
    @Timed(
            name = "average-transaction",
            unit = MetricUnits.SECONDS,
            description = "Average duration of transaction"
    )
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doTransaction(Transaction transaction) {
        if (transaction.amount > highestTransaction) {
            highestTransaction = transaction.amount;
        }
        transactionHistogram.update(transaction.amount);
        return Response.ok().build();
    }

    @Gauge(
            name = "highest-gross-transaction",
            description = "Highest transaction so far.",
            unit = MetricUnits.NONE
    )
    public long highestTransaction() {
        return highestTransaction;
    }

}
