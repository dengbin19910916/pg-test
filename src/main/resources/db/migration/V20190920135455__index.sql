create index trade_order_created_time_index on trade_order (created_time) where status <> '4';
create index trade_order_status_index on trade_order using btree (status) where status <> '4';
create index trade_order_receiver_name_index on trade_order using btree (receiver_name) where status <> '4';
create index trade_order_buyer_memo_index on trade_order using gin (buyer_memo gin_trgm_ops) where status <> '4';

create index order_item_order_id on order_item using btree (order_id) where order_status <> '4';
create index order_item_order_created_time_index on order_item (order_created_time) where order_status <> '4';
create index order_item_order_status_index on order_item (order_status) where order_status <> '4';
create index order_item_given_index on order_item (given) where order_status <> '4';
