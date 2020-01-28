package com.example.bluetoothclient;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;

import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

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
        playSound(primaryCode);
        InputConnection inputConnection = getCurrentInputConnection();

        CharSequence currentText;
        CharSequence beforCursorText;
        CharSequence afterCursorText;

        if (inputConnection != null) {
            switch (primaryCode) {
                case 1:
                    inputConnection.commitText("Hello I’m custom bluetooth keyboard!!!", 0);
                    break;
                case 2:
                    CharSequence selectedText = inputConnection.getSelectedText(0);
                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0);
                    } else {
                        inputConnection.commitText("", 1);
                    }
                    break;
                case 3:
                    inputConnection.commitText("616000010000123", 0);
                    break;
                case 4:
                    inputConnection.commitText("616000010000124", 0);
                    break;
                case 5:
                    currentText = inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text;
                    beforCursorText = inputConnection.getTextBeforeCursor(currentText.length(), 0);
                    afterCursorText = inputConnection.getTextAfterCursor(currentText.length(), 0);
                    inputConnection.deleteSurroundingText(beforCursorText.length(), afterCursorText.length());
                    inputConnection.commitText("OPEN_CAMERA_INTENT", 0);
                    MainActivity.clickSend();
                    break;
                case 6:
                    InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                    assert imeManager != null;
                    imeManager.showInputMethodPicker();
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

    private void playSound(int keyCode){
        float volume = 0.5f;
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if (keyCode == 2) {
            audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE, volume);
        } else audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD, volume);
    }

}
