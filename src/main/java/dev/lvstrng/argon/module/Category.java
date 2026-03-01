package dev.lvstrng.argon.module;

import dev.lvstrng.argon.utils.EncryptedString;

public enum Category {
	COMBAT(EncryptedString.of("Nethpot")),
	CLIENT(EncryptedString.of("Client"));
	public final CharSequence name;

	Category(CharSequence name) {
		this.name = name;
	}
}
