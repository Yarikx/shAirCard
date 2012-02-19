/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/unlogic/work/projects/Android/shAirCard/enroscar-sources/src/com/stanfy/app/service/ApiMethodCallback.aidl
 */
package com.stanfy.app.service;
public interface ApiMethodCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.stanfy.app.service.ApiMethodCallback
{
private static final java.lang.String DESCRIPTOR = "com.stanfy.app.service.ApiMethodCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.stanfy.app.service.ApiMethodCallback interface,
 * generating a proxy if needed.
 */
public static com.stanfy.app.service.ApiMethodCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.stanfy.app.service.ApiMethodCallback))) {
return ((com.stanfy.app.service.ApiMethodCallback)iin);
}
return new com.stanfy.app.service.ApiMethodCallback.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_reportSuccess:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
com.stanfy.serverapi.response.ResponseData _arg2;
if ((0!=data.readInt())) {
_arg2 = com.stanfy.serverapi.response.ResponseData.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.reportSuccess(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_reportError:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
com.stanfy.serverapi.response.ResponseData _arg2;
if ((0!=data.readInt())) {
_arg2 = com.stanfy.serverapi.response.ResponseData.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.reportError(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_reportPending:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.reportPending(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_reportLastOperation:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
com.stanfy.serverapi.response.ResponseData _arg2;
if ((0!=data.readInt())) {
_arg2 = com.stanfy.serverapi.response.ResponseData.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.reportLastOperation(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.stanfy.app.service.ApiMethodCallback
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
   * Notify about successful operation finish. 
   */
public void reportSuccess(int token, int operation, com.stanfy.serverapi.response.ResponseData response) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(token);
_data.writeInt(operation);
if ((response!=null)) {
_data.writeInt(1);
response.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_reportSuccess, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
   * Notify about operation error. 
   */
public void reportError(int token, int operation, com.stanfy.serverapi.response.ResponseData response) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(token);
_data.writeInt(operation);
if ((response!=null)) {
_data.writeInt(1);
response.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_reportError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
   * Notify about pending operation. This method can be called when a callback is registered. 
   */
public void reportPending(int token, int operation) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(token);
_data.writeInt(operation);
mRemote.transact(Stub.TRANSACTION_reportPending, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
   * Notify about last operation. Response data will NOT contain model!
   */
public void reportLastOperation(int token, int operation, com.stanfy.serverapi.response.ResponseData response) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(token);
_data.writeInt(operation);
if ((response!=null)) {
_data.writeInt(1);
response.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_reportLastOperation, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_reportSuccess = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_reportError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_reportPending = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_reportLastOperation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
/**
   * Notify about successful operation finish. 
   */
public void reportSuccess(int token, int operation, com.stanfy.serverapi.response.ResponseData response) throws android.os.RemoteException;
/**
   * Notify about operation error. 
   */
public void reportError(int token, int operation, com.stanfy.serverapi.response.ResponseData response) throws android.os.RemoteException;
/**
   * Notify about pending operation. This method can be called when a callback is registered. 
   */
public void reportPending(int token, int operation) throws android.os.RemoteException;
/**
   * Notify about last operation. Response data will NOT contain model!
   */
public void reportLastOperation(int token, int operation, com.stanfy.serverapi.response.ResponseData response) throws android.os.RemoteException;
}
