package com.siziksu.payment.data.mapper;

import com.siziksu.payment.common.mapper.Mapper;
import com.siziksu.payment.data.client.model.Character;
import com.siziksu.payment.data.model.CharacterData;

public final class CharacterMapper extends Mapper<Character, CharacterData> {

    @Override
    public CharacterData map(Character unmapped) {
        CharacterData mapped = new CharacterData();
        mapped.id = unmapped.id;
        mapped.name = unmapped.name;
        mapped.thumbnailPath = unmapped.thumbnail.path;
        mapped.thumbnailExtension = unmapped.thumbnail.extension;
        return mapped;
    }

    @Override
    public Character unMap(CharacterData mapped) {
        Character unmapped = new Character();
        unmapped.id = mapped.id;
        unmapped.name = mapped.name;
        unmapped.thumbnail.path = mapped.thumbnailPath;
        unmapped.thumbnail.extension = mapped.thumbnailExtension;
        return unmapped;
    }
}
