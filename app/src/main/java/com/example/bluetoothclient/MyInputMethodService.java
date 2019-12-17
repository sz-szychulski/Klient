package com.example.bluetoothclient;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    @Override
    public View onCreateInputView() {
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.page);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();
        CharSequence currentText;
        CharSequence beforCursorText;
        CharSequence afterCursorText;

        if (inputConnection != null) {
            switch (primaryCode) {
                case 1:
                    currentText = inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text;
                    beforCursorText = inputConnection.getTextBeforeCursor(currentText.length(), 0);
                    afterCursorText = inputConnection.getTextAfterCursor(currentText.length(), 0);
                    inputConnection.deleteSurroundingText(beforCursorText.length(), afterCursorText.length());
                    inputConnection.commitText("Code 18923232A", 0);
                    break;
                case 2:
                    currentText = inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text;
                    beforCursorText = inputConnection.getTextBeforeCursor(currentText.length(), 0);
                    afterCursorText = inputConnection.getTextAfterCursor(currentText.length(), 0);
                    inputConnection.deleteSurroundingText(beforCursorText.length(), afterCursorText.length());
                    inputConnection.commitText("Code 18923232B", 0);
                    break;
                case 3:
                    final Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(cameraIntent);
                    Toast cameraToast = Toast.makeText(this, "Opening camera", Toast.LENGTH_SHORT);
                    cameraToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0 , 800);
                    cameraToast.show();
                    break;
                case 4:
                    String toSave = "This text will be save";
                    File file = new File(getExternalFilesDir(null), "keyboard.txt");
                    try {
                        Toast beforeSaveToast = Toast.makeText(this, "Saving file", Toast.LENGTH_SHORT);
                        beforeSaveToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 800);
                        beforeSaveToast.show();

                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                        fileOutputStream.write(toSave.getBytes());
                        fileOutputStream.flush();
                        fileOutputStream.close();

                        Toast afterSaveToast = Toast.makeText(this, "Saved", Toast.LENGTH_SHORT);
                        afterSaveToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 800);
                        afterSaveToast.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast errorSaveToast = Toast.makeText(this, "Something went wrong with saving file", Toast.LENGTH_SHORT);
                        errorSaveToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 800);
                        errorSaveToast.show();
                    }
                    break;
                case 5:
                    final Intent BROWSER_INTENT = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                    startActivity(BROWSER_INTENT);
                    break;
                case 6:
                    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                    if (adapter.isEnabled()) {
                        adapter.disable();
                        Toast bluetoothDisable = Toast.makeText(this, "Bluetooth disabled", Toast.LENGTH_SHORT);
                        bluetoothDisable.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 800);
                        bluetoothDisable.show();
                    } else {
                        adapter.enable();
                        Toast bluetoothEnable = Toast.makeText(this, "Bluetooth enabled", Toast.LENGTH_SHORT);
                        bluetoothEnable.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 800);
                        bluetoothEnable.show();
                    }
                    break;
            }
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
