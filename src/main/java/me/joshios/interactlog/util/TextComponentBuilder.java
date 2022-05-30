package me.joshios.interactlog.util;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class TextComponentBuilder {

    private TextComponent component;
    private String text;

    private String click;
    private ClickEvent.Action clickAction;

    private String hover;
    private HoverEvent.Action hoverAction;

    public TextComponentBuilder() {
        component = new TextComponent();
    }

    public TextComponentBuilder click(String click) {
        return click(click, ClickEvent.Action.RUN_COMMAND);
    }

    public TextComponentBuilder click(String clickText, ClickEvent.Action action) {
        click = clickText;
        clickAction = action;
        return this;
    }

    public TextComponentBuilder hover(String hover) {
        return hover(hover, HoverEvent.Action.SHOW_TEXT);
    }

    public TextComponentBuilder hover(String hoverText, HoverEvent.Action action) {
        hover = hoverText;
        hoverAction = action;
        return this;
    }

    public TextComponentBuilder text(String message) {
        text = message;
        return this;
    }

    public TextComponentBuilder assemble() {
        if (text == null) {
            return this;
        }

        TextComponent textComponent = new TextComponent(text);

        if (hover != null) {
            HoverEvent.Action hoverAction = this.hoverAction == null ? HoverEvent.Action.SHOW_TEXT : this.hoverAction;
            textComponent.setHoverEvent(new HoverEvent(hoverAction, new Text(this.hover)));
        }

        if (click != null) {
            ClickEvent.Action clickAction = this.clickAction == null ? ClickEvent.Action.RUN_COMMAND : this.clickAction;
            textComponent.setClickEvent(new ClickEvent(clickAction, click));
        }

        component.addExtra(textComponent);

        text = null;
        click = null;
        clickAction = null;
        hover = null;
        hoverAction = null;

        return this;
    }

    public TextComponentBuilder reset() {
        component = new TextComponent();
        return this;
    }


    public TextComponent build() {
        assemble();
        return component;
    }
}
