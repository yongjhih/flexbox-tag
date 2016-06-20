# FlexBox Tag

[![JitPack](https://img.shields.io/github/tag/yongjhih/flexbox-tag.svg?label=JitPack)](https://jitpack.io/#yongjhih/flexbox-tag)
[![javadoc](https://img.shields.io/github/tag/yongjhih/flexbox-tag.svg?label=javadoc)](https://jitpack.io/com/github/yongjhih/flexbox-tag/-SNAPSHOT/javadoc/)
[![Build Status](https://travis-ci.org/yongjhih/flexbox-tag.svg)](https://travis-ci.org/yongjhih/flexbox-tag)
<!--[![Coverage Status](https://coveralls.io/repos/github/yongjhih/flexbox-tag/badge.svg)](https://coveralls.io/github/yongjhih/flexbox-tag)-->

## Usage

```java
TagFlowLayout.Adapter<String> tagAdapter = TagFlowLayout.Adapter.create();
tagAdapter.getList().add("Tab1");
tagAdapter.createViewHolder((parent, viewType) -> new TagViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tag, parent, false)));
tagFlowLayout.setAdapter(tagAdapter);
tagAdapter.getList().add("Tag2").notifyChanged();

public class TagViewHolder extends BindViewHolder<String> {
    @InjectView(R.id.text)
    public TextView text;

    public IconViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void onBind(int position, String item) {
        text.setText(item);
    }
}
```

## Installation

via jitpack:

```gradle
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.yongjhih:flexbox-tag:-SNAPSHOT'
    //compile 'com.github.yongjhih:flexbox-tag:0.0.1'
}
```

## License

```
Copyright 2016 8tory, Inc

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
