-- 订单表
create table trade_order
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
    includes_gift          boolean       not null,
    created_time           timestamp     not null,
    updated_time           timestamp     not null,
    constraint trade_order_pk primary key (id, channel) -- 分区表的分区键必须包含在主键中
) partition by list (channel);

create table trade_order_tm partition of trade_order for values in ('1');
create table trade_order_jd_vip partition of trade_order for values in ('2', '3', '4');

-- create table trade_order_tm partition of trade_order for values in ('1') partition by range (created_time);
-- create table trade_order_tm_201901 partition of trade_order_tm for values from ('2019-01-01') to ('2019-04-01');
-- create table trade_order_tm_201904 partition of trade_order_tm for values from ('2019-04-01') to ('2019-07-01');
-- create table trade_order_tm_201907 partition of trade_order_tm for values from ('2019-07-01') to ('2019-10-01');
-- create table trade_order_tm_201910 partition of trade_order_tm for values from ('2019-10-01') to ('2020-01-01');
--
-- create table trade_order_jd_vip partition of trade_order for values in ('2', '3', '4') partition by range (created_time);
-- create table trade_order_jd_vip_201901 partition of trade_order_jd_vip for values from ('2019-01-01') to ('2019-04-01');
-- create table trade_order_jd_vip_201904 partition of trade_order_jd_vip for values from ('2019-04-01') to ('2019-07-01');
-- create table trade_order_jd_vip_201907 partition of trade_order_jd_vip for values from ('2019-07-01') to ('2019-10-01');
-- create table trade_order_jd_vip_201910 partition of trade_order_jd_vip for values from ('2019-10-01') to ('2020-01-01');


comment on table trade_order is '订单表';

comment on column trade_order.id is '订单ID';
comment on column trade_order.channel is '渠道';
comment on column trade_order.out_id is '外部订单ID';
comment on column trade_order.shop_code is '店铺编码';
comment on column trade_order.shop_name is '店铺名称';
comment on column trade_order.paid_time is '支付时间';
comment on column trade_order.buyer_name is '买家昵称';
comment on column trade_order.buyer_memo is '买家备注';
comment on column trade_order.receiver_name is '收货人昵称';
comment on column trade_order.receiver_mobile is '收货人手机号';
comment on column trade_order.receiver_country_code is '收货人国家编码';
comment on column trade_order.receiver_province_code is '收货人省份编码';
comment on column trade_order.receiver_city_code is '收货人城市编码';
comment on column trade_order.receiver_area_code is '收货人地区编码';
comment on column trade_order.receiver_address is '收货人详细地址';
comment on column trade_order.receiver_zip is '收货人邮政编码';
comment on column trade_order.status is '订单状态';
comment on column trade_order.includes_gift is '订单是否包含赠品';
comment on column trade_order.created_time is '创建时间';
comment on column trade_order.updated_time is '最后时间';


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
    constraint order_item_pk
        primary key (id, order_channel)
) partition by list (order_channel);

create table order_item_tm partition of order_item for values in ('1');
create table order_item_jd_vip partition of order_item for values in ('2', '3', '4');

-- create table order_item_tm partition of order_item for values in ('1') partition by range (order_created_time);
-- create table order_item_tm_201901 partition of order_item_tm for values from ('2019-01-01') to ('2019-04-01');
-- create table order_item_tm_201904 partition of order_item_tm for values from ('2019-04-01') to ('2019-07-01');
-- create table order_item_tm_201907 partition of order_item_tm for values from ('2019-07-01') to ('2019-10-01');
-- create table order_item_tm_201910 partition of order_item_tm for values from ('2019-10-01') to ('2020-01-01');
--
-- create table order_item_jd_vip partition of order_item for values in ('2', '3', '4') partition by range (order_created_time);
-- create table order_item_jd_vip_201901 partition of order_item_jd_vip for values from ('2019-01-01') to ('2019-04-01');
-- create table order_item_jd_vip_201904 partition of order_item_jd_vip for values from ('2019-04-01') to ('2019-07-01');
-- create table order_item_jd_vip_201907 partition of order_item_jd_vip for values from ('2019-07-01') to ('2019-10-01');
-- create table order_item_jd_vip_201910 partition of order_item_jd_vip for values from ('2019-10-01') to ('2020-01-01');


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
--     add constraint order_item_trade_order_id_fk
--         foreign key (order_id) references trade_order (id);


-- 代码字典
drop table if exists code_dict;

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