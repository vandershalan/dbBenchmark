-- Create table
create table RFID_EVENTS
(
  event_id NUMBER not null,
  epc      VARCHAR2(24) not null,
  item_id  VARCHAR2(128) not null,
  token_sn NUMBER not null
)
tablespace RETEK_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
