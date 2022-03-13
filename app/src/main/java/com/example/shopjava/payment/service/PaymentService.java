package com.example.shopjava.payment.service;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.shopjava.login.service.UserService;
import com.example.shopjava.order.request.OrderRequest;
import com.example.shopjava.order.ui.OrderViewModel;
import com.example.shopjava.payment.paypal.Config;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.config.CheckoutConfig;
import com.paypal.checkout.config.Environment;
import com.paypal.checkout.createorder.CreateOrderActions;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.OrderIntent;
import com.paypal.checkout.createorder.ProcessingInstruction;
import com.paypal.checkout.createorder.UserAction;
import com.paypal.checkout.order.Amount;
import com.paypal.checkout.order.AppContext;
import com.paypal.checkout.order.Order;
import com.paypal.checkout.order.PurchaseUnit;
import com.paypal.checkout.paymentbutton.PayPalButton;

import java.util.ArrayList;
import java.util.Locale;

public class PaymentService {

    private static PaymentService INSTANCE;

    private String paymentMethod;

    private int totalPrice;

    private Context context;

    private FragmentActivity fragmentActivity;

    private OrderViewModel orderViewModel;

    private String token;


    public static PaymentService getInstance() {

        if (INSTANCE == null) {

            return new PaymentService();
        }
        return INSTANCE;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public void setFragmentActivity(FragmentActivity fragmentActivity) {

        this.fragmentActivity = fragmentActivity;
    }

    public void paymentChanged(CompoundButton buttonView, boolean isChecked,
                               OrderRequest orderRequest) {

        if (isChecked) {

            paymentMethod = (String) buttonView.getText();

            orderRequest.setPaymentMethod(paymentMethod);
        }
    }

    public void applyCashback(CompoundButton buttonView, boolean isChecked,
                              OrderRequest orderRequest) {

        if (isChecked) {

            UserService userService = UserService.getInstance();

            userService.setContext(buttonView.getContext());

            if ((totalPrice * 0.30) >= userService.getCashback()) {

                orderRequest.setCashBackApplied(userService.getCashback());
            } else {

                double orderCashBackMax = totalPrice * 0.30 + totalPrice;
                orderRequest.setCashBackApplied((float) (orderCashBackMax - totalPrice));
            }
        } else {

            orderRequest.setCashBackApplied(0);
        }
    }

    public void pay(View view, OrderRequest orderRequest) {

        if (paymentMethod == null) {

            Toast.makeText(view.getContext(), "Please select a payment method!",
                    Toast.LENGTH_SHORT).show();
        } else {

            orderViewModel.createOrder(token, orderRequest);

        }

    }

    public void setTotalPrice(int totalPrice) {

        this.totalPrice = totalPrice;
    }

    public void setupPayPal(PayPalButton payPalButton, BottomSheetDialog bottomSheetDialog) {

        CheckoutConfig checkoutConfig =
                new CheckoutConfig(
                        fragmentActivity.getApplication(),
                        Config.PAYPAL_CLIENT_ID,
                        Environment.SANDBOX,
                        "com.example.shopjava://paypalpay",
                        CurrencyCode.USD,
                        UserAction.PAY_NOW
                );

        payPalButton.setup(
                createOrderActions -> {
                    ArrayList<PurchaseUnit> purchaseUnits = new ArrayList<>();
                    purchaseUnits.add(
                            new PurchaseUnit.Builder()
                                    .amount(
                                            new Amount.Builder()
                                                    .currencyCode(CurrencyCode.USD)
                                                    .value(String.format(Locale.ENGLISH,
                                                            "%.2f",
                                                            totalPrice / 20.18))
                                                    .build()
                                    )
                                    .build()
                    );

                    Order order = new Order(
                            OrderIntent.CAPTURE,
                            new AppContext.Builder().userAction(UserAction.PAY_NOW).build(),
                            purchaseUnits,
                            ProcessingInstruction.ORDER_COMPLETE_ON_PAYMENT_APPROVAL
                    );

                    createOrderActions.create(order, (CreateOrderActions.OnOrderCreated) null);
                },
                approval -> approval.getOrderActions().capture(result -> {

                    bottomSheetDialog.cancel();
                }));


        PayPalCheckout.setConfig(checkoutConfig);

    }

    public void setOrderViewModel(OrderViewModel orderViewModel) {

        this.orderViewModel = orderViewModel;
    }

    public void setToken(String token) {

        this.token = token;
    }
}
