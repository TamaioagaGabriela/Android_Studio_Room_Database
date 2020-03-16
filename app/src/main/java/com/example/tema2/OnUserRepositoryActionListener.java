package com.example.tema2;

// You can use an interface like this to perform actions on main thread
// when the action is done.
public interface OnUserRepositoryActionListener {
    void actionSuccess();
    void actionFaailed();
}