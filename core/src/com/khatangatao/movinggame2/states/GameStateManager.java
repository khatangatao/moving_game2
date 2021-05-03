package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private final Stack<State> stateStack;

    public GameStateManager() {
        stateStack = new Stack<>();
    }

    public void push(State state) {
        stateStack.push(state);
    }

    public void pop() {
        stateStack.pop().dispose();
    }

    public void set(State state) {
        stateStack.pop().dispose();
        stateStack.push(state);
    }

    public void update(float dt) {
        stateStack.peek().update(dt);

    }

    public void render(SpriteBatch spriteBatch) {
        stateStack.peek().render(spriteBatch);
    }
}
