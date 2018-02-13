package app.backing;

import app.model.BitcoinUIModel;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.bitcoinj.core.*;
import org.bitcoinj.core.listeners.DownloadProgressTracker;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.SendRequest;
import org.bitcoinj.wallet.Wallet;
import org.spongycastle.crypto.params.KeyParameter;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static app.backing.MoneyHandlerBean.bitcoin;
import static com.google.common.base.Preconditions.checkState;


public class MoneyHandlerBean{

    MainController controller;

    public static WalletAppKit bitcoin;
    public static NetworkParameters params = MainNetParams.get();
    public static final String APP_NAME = "WalletTemplate";
    private static final String WALLET_FILE_NAME = APP_NAME.replaceAll("[^a-zA-Z0-9.-]", "_") + "-"
            + params.getPaymentProtocolId();

        @PostConstruct
        private void init() throws Exception {
            realStart();
        }


        private void realStart() throws IOException {

            //URL location = getClass().getResource("main.fxml");
            //FXMLLoader loader = new FXMLLoader(location);
            //controller = loader.getController();
            // Configure the window with a StackPane so we can overlay things on top of the main UI, and a
            // NotificationBarPane so we can slide messages and progress bars in from the bottom. Note that
            // ordering of the construction and connection matters here, otherwise we get (harmless) CSS error
            // spew to the logs.
            Threading.USER_THREAD = Platform::runLater;
            // Create the app kit. It won't do any heavyweight initialization until after we start it.
            setupWalletKit(null);

            if (bitcoin.isChainFileLocked()) {
                System.out.println("Already running This application is already running and cannot be started twice.");
                Platform.exit();
                return;
            }


            //WalletSetPasswordController.estimateKeyDerivationTimeMsec();

            bitcoin.startAsync();

        }

        public void setupWalletKit(@Nullable DeterministicSeed seed) {
            // If seed is non-null it means we are restoring from backup.
            bitcoin = new WalletAppKit(params, new File("."), WALLET_FILE_NAME) {
                @Override
                protected void onSetupCompleted() {
                    // Don't make the user wait for confirmations for now, as the intention is they're sending it
                    // their own money!
                    bitcoin.wallet().allowSpendingUnconfirmedTransactions();
                    Platform.runLater(controller::onBitcoinSetup);
                }
            };
            // Now configure and start the appkit. This will take a second or two - we could show a temporary splash screen
            // or progress widget to keep the user engaged whilst we initialise, but we don't.
            if (params == RegTestParams.get()) {
                bitcoin.connectToLocalHost();   // You should run a regtest mode bitcoind locally.
            }
//            bitcoin.setDownloadListener(controller.progressBarUpdater())
//                    .setBlockingStartup(false)
//                    .setUserAgent(APP_NAME, "1.0");
            if (seed != null)
                bitcoin.restoreWalletFromSeed(seed);
        }
}


class MainController {
    private BitcoinUIModel model = new BitcoinUIModel();


    public void initialize() {
      //  addressControl.setOpacity(0.0);
    }

    public void onBitcoinSetup() {
        model.setWallet(bitcoin.wallet());
//        addressControl.addressProperty().bind(model.addressProperty());
//        balance.textProperty().bind(EasyBind.map(model.balanceProperty(), coin -> MonetaryFormat.BTC.noCode().format(coin).toString()));
//        // Don't let the user click send money when the wallet is empty.
//        sendMoneyOutBtn.disableProperty().bind(model.balanceProperty().isEqualTo(Coin.ZERO));
//
//        showBitcoinSyncMessage();
//        model.syncProgressProperty().addListener(x -> {
//            if (model.syncProgressProperty().get() >= 1.0) {
//       //         readyToGoAnimation();
//                if (syncItem != null) {
//                    syncItem.cancel();
//                    syncItem = null;
//                }
//            } else if (syncItem == null) {
//                showBitcoinSyncMessage();
//            }
//        });
    }

    private void showBitcoinSyncMessage() {
        //syncItem = Main.instance.notificationBar.pushItem("Synchronising with the Bitcoin network", model.syncProgressProperty());
    }

    public void sendMoneyOut(ActionEvent event) {
        // Hide this UI and show the send money UI. This UI won't be clickable until the user dismisses send_money.
        //TODO sending money fxml?? Main.instance.overlayUI("send_money.fxml");
    }

//    public void settingsClicked(ActionEvent event) {
//        Main.OverlayUI<WalletSettingsController> screen = Main.instance.overlayUI("wallet_settings.fxml");
//        screen.controller.initialize(null);
//    }

//    public void restoreFromSeedAnimation() {
//        // Buttons slide out ...
//        TranslateTransition leave = new TranslateTransition(Duration.millis(1200), controlsBox);
//        leave.setByY(80.0);
//        leave.play();
//    }

//    public void readyToGoAnimation() {
//        // Buttons slide in and clickable address appears simultaneously.
//        TranslateTransition arrive = new TranslateTransition(Duration.millis(1200), controlsBox);
//        arrive.setInterpolator(new ElasticInterpolator(EasingMode.EASE_OUT, 1, 2));
//        arrive.setToY(0.0);
//        FadeTransition reveal = new FadeTransition(Duration.millis(1200), addressControl);
//        reveal.setToValue(1.0);
//        ParallelTransition group = new ParallelTransition(arrive, reveal);
//        group.setDelay(NotificationBarPane.ANIM_OUT_DURATION);
//        group.setCycleCount(1);
//        group.play();
//    }

    public DownloadProgressTracker progressBarUpdater() {
        return model.getDownloadProgressTracker();
    }
}

/*
 * Copyright by the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



public class SendMoneyController {
    public Button sendBtn;
    public Button cancelBtn;
    public TextField address;
    public Label titleLabel;
    public TextField amountEdit;
    public Label btcLabel;

//    public Main.OverlayUI overlayUI;

    private Wallet.SendResult sendResult;
    private KeyParameter aesKey;

    // Called by FXMLLoader
    public void initialize() {
        Coin balance = bitcoin.wallet().getBalance();
        checkState(!balance.isZero());
        new BitcoinAddressValidator(MoneyHandlerBean.params, address);
//        new TextFieldValidator(amountEdit, text ->
//                !WTUtils.didThrow(() -> checkState(Coin.parseCoin(text).compareTo(balance) <= 0)));
        amountEdit.setText(balance.toPlainString());
    }

//    public void cancel(ActionEvent event) {
//        overlayUI.done();
//    }

    public void send(ActionEvent event) {
        // Address exception cannot happen as we validated it beforehand.
        try {
            Coin amount = Coin.parseCoin(amountEdit.getText());
            Address destination = Address.fromBase58(MoneyHandlerBean.params, address.getText());
            SendRequest req;
            if (amount.equals(bitcoin.wallet().getBalance()))
                req = SendRequest.emptyWallet(destination);
            else
                req = SendRequest.to(destination, amount);
            req.aesKey = aesKey;
            sendResult = bitcoin.wallet().sendCoins(req);
            Futures.addCallback(sendResult.broadcastComplete, new FutureCallback<Transaction>() {
                @Override
                public void onSuccess(@Nullable Transaction result) {
   //                 checkGuiThread();
 //                   overlayUI.done();
                }

                @Override
                public void onFailure(Throwable t) {
                    // We died trying to empty the wallet.
  //                  crashAlert(t);
                }
            });
            sendResult.tx.getConfidence().addEventListener((tx, reason) -> {
                if (reason == TransactionConfidence.Listener.ChangeReason.SEEN_PEERS) {}
    //                updateTitleForBroadcast();
            });
            sendBtn.setDisable(true);
            address.setDisable(true);
            ((HBox)amountEdit.getParent()).getChildren().remove(amountEdit);
            ((HBox)btcLabel.getParent()).getChildren().remove(btcLabel);
            //updateTitleForBroadcast();
        } catch (InsufficientMoneyException e) {
//            informationalAlert("Could not empty the wallet",
//                    "You may have too little money left in the wallet to make a transaction.");
//            overlayUI.done();
        } catch (ECKey.KeyIsEncryptedException e) {

        }
    }

}
class BitcoinAddressValidator {
    private NetworkParameters params;
//    private Node[] nodes;

    public BitcoinAddressValidator(NetworkParameters params, TextField field ) {
        this.params = params;
//        this.nodes = nodes;

        // Handle the red highlighting, but don't highlight in red just when the field is empty because that makes
        // the example/prompt address hard to read.
        //new TextFieldValidator(field, text -> text.isEmpty() || testAddr(text));
        // However we do want the buttons to be disabled when empty so we apply a different test there.
        field.textProperty().addListener((observableValue, prev, current) -> {
            toggleButtons(current);
        });
        toggleButtons(field.getText());
    }

    private void toggleButtons(String current) {
        boolean valid = testAddr(current);
      //  for (Node n : nodes) n.setDisable(!valid);
    }

    private boolean testAddr(String text) {
        try {
            Address.fromBase58(params, text);
            return true;
        } catch (AddressFormatException e) {
            return false;
        }
    }
}
