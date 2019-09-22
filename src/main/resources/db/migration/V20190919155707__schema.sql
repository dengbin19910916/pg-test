-- 订单表
create table "order"
(
    id                     bigserial     not null,
    channel                varchar(4)    not null,
    out_id                 varchar(100)  not null,
    shop_code              varchar(50)   not null,
    shop_name              varchar(200)  not null,
    paid_time              timestamp     null,
    buyer_name             varchar(200)  not null,
    buyer_memo             varchar(2000) null,
    receiver_name          varchar(200)  not null,
    receiver_mobile        varchar(20)   not null,
    receiver_country_code  varchar(50)   null,
    receiver_city_code     varchar(50)   null,
    receiver_province_code varchar(50)   null,
    receiver_area_code     varchar(50)   null,
    receiver_address       varchar(50)   null,
    receiver_zip           varchar(50)   null,
    status                 varchar(4)    not null,
    includes_gift          boolean       not null default false,
    created_time           timestamp     not null,
    updated_time           timestamp     not null,
    sync_time              timestamp     not null,
    constraint order_pk primary key (id)
);

comment on table "order" is '订单表';

comment on column "order".id is '订单ID';
comment on column "order".channel is '渠道';
comment on column "order".out_id is '外部订单ID';
comment on column "order".shop_code is '店铺编码';
comment on column "order".shop_name is '店铺名称';
comment on column "order".paid_time is '支付时间';
comment on column "order".buyer_name is '买家昵称';
comment on column "order".buyer_memo is '买家备注';
comment on column "order".receiver_name is '收货人昵称';
comment on column "order".receiver_mobile is '收货人手机号';
comment on column "order".receiver_country_code is '收货人国家编码';
comment on column "order".receiver_province_code is '收货人省份编码';
comment on column "order".receiver_city_code is '收货人城市编码';
comment on column "order".receiver_area_code is '收货人地区编码';
comment on column "order".receiver_address is '收货人详细地址';
comment on column "order".receiver_zip is '收货人邮政编码';
comment on column "order".status is '订单状态';
comment on column "order".includes_gift is '订单是否包含赠品';
comment on column "order".created_time is '创建时间';
comment on column "order".updated_time is '最后时间';


-- 订单明细表
create table order_item
(
    id                 bigserial      not null,
    order_id           bigint         not null,
    order_channel      varchar(4)     not null,
    serial             int            not null,
    sku_id             varchar(50)    not null,
    name               varchar(200)   not null,
    quantity           int            not null,
    tag_price          decimal(16, 2) not null,
    purchase_price     decimal(16, 2) not null,
    given              boolean        not null default false,
    order_created_time timestamp      not null,
    order_status       varchar(4)     not null,
    created_time       timestamp      not null,
    updated_time       timestamp      not null,
    constraint order_item_pk primary key (id)
);

comment on table order_item is '订单明细表';

comment on column order_item.id is '订单明细ID';
comment on column order_item.order_id is '订单ID';
comment on column order_item.order_channel is '订单渠道';
comment on column order_item.sku_id is 'SKU ID';
comment on column order_item.name is '商品名称';
comment on column order_item.quantity is 'SKU数量';
comment on column order_item.tag_price is '商品吊牌价';
comment on column order_item.purchase_price is '商品实际购买价';
comment on column order_item.given is '是否赠送';
comment on column order_item.order_created_time is '订单创建时间';
comment on column order_item.order_status is '订单状态';
comment on column order_item.created_time is '创建时间';
comment on column order_item.updated_time is '最后更新时间';

-- alter table order_item
--     add constraint order_item_order_id_fk
--         foreign key (order_id) references "order" (id);

-- 代码字典表
create table code_dict
(
    code   varchar(100) not null,
    name   varchar(100) not null,
    value  varchar(100) not null,
    serial int          not null,
    constraint code_dict_pk
        primary key (code, name, value, serial)
);

comment on table code_dict is '代码字典';

comment on column code_dict.code is '编码';
comment on column code_dict.name is '名称';
comment on column code_dict.value is '值';
comment on column code_dict.serial is '排序序号';


-- 订单历史表
create table history_order
(
    id                     bigserial     not null,
    channel                varchar(4)    not null,
    out_id                 varchar(100)  not null,
    shop_code              varchar(50)   not null,
    shop_name              varchar(200)  not null,
    paid_time              timestamp     null,
    buyer_name             varchar(200)  not null,
    buyer_memo             varchar(2000) null,
    receiver_name          varchar(200)  not null,
    receiver_mobile        varchar(20)   not null,
    receiver_country_code  varchar(50)   null,
    receiver_city_code     varchar(50)   null,
    receiver_province_code varchar(50)   null,
    receiver_area_code     varchar(50)   null,
    receiver_address       varchar(50)   null,
    receiver_zip           varchar(50)   null,
    includes_gift          boolean       not null default false,
    created_time           timestamp     not null,
    updated_time           timestamp     not null,
    constraint history_order_pk primary key (id)
);

comment on table history_order is '历史订单表';

comment on column history_order.id is '订单ID';
comment on column history_order.channel is '渠道';
comment on column history_order.out_id is '外部订单ID';
comment on column history_order.shop_code is '店铺编码';
comment on column history_order.shop_name is '店铺名称';
comment on column history_order.paid_time is '支付时间';
comment on column history_order.buyer_name is '买家昵称';
comment on column history_order.buyer_memo is '买家备注';
comment on column history_order.receiver_name is '收货人昵称';
comment on column history_order.receiver_mobile is '收货人手机号';
comment on column history_order.receiver_country_code is '收货人国家编码';
comment on column history_order.receiver_province_code is '收货人省份编码';
comment on column history_order.receiver_city_code is '收货人城市编码';
comment on column history_order.receiver_area_code is '收货人地区编码';
comment on column history_order.receiver_address is '收货人详细地址';
comment on column history_order.receiver_zip is '收货人邮政编码';
comment on column history_order.includes_gift is '订单是否包含赠品';
comment on column history_order.created_time is '创建时间';


-- 历史订单明细表
create table history_order_item
(
    id                 bigserial      not null,
    order_id           bigint         not null,
    order_channel      varchar(4)     not null,
    serial             int            not null,
    sku_id             varchar(50)    not null,
    name               varchar(200)   not null,
    quantity           int            not null,
    tag_price          decimal(16, 2) not null,
    purchase_price     decimal(16, 2) not null,
    given              boolean        not null default false,
    order_created_time timestamp      not null,
    order_status       varchar(4)     not null,
    created_time       timestamp      not null,
    updated_time       timestamp      not null,
    constraint history_order_item_pk primary key (id)
);

comment on table history_order_item is '历史订单明细表';

comment on column history_order_item.id is '订单明细ID';
comment on column history_order_item.order_id is '订单ID';
comment on column history_order_item.order_channel is '订单渠道';
comment on column history_order_item.sku_id is 'SKU ID';
comment on column history_order_item.name is '商品名称';
comment on column history_order_item.quantity is 'SKU数量';
comment on column history_order_item.tag_price is '商品吊牌价';
comment on column history_order_item.purchase_price is '商品实际购买价';
comment on column history_order_item.given is '是否赠送';
comment on column history_order_item.order_created_time is '订单创建时间';
comment on column history_order_item.order_status is '订单状态';
comment on column history_order_item.created_time is '创建时间';
comment on column history_order_item.updated_time is '最后更新时间';

-- alter table history_order_item
--     add constraint history_order_item_order_id_fk
--         foreign key (order_id) references history_order (id);