/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/unlogic/work/projects/Android/shAirCard/enroscar-sources/src/com/stanfy/app/service/ApiMethods.aidl
 */
package com.stanfy.app.service;
public interface ApiMethods extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.stanfy.app.service.ApiMethods
{
private static final java.lang.String DESCRIPTOR = "com.stanfy.app.service.ApiMethods";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.stanfy.app.service.ApiMethods interface,
 * generating a proxy if needed.
 */
public static com.stanfy.app.service.ApiMethods asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.stanfy.app.service.ApiMethods))) {
return ((com.stanfy.app.service.ApiMethods)iin);
}
return new com.stanfy.app.service.ApiMethods.Stub.Proxy(obj);
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
case TRANSACTION_performRequest:
{
data.enforceInterface(DESCRIPTOR);
com.stanfy.serverapi.request.RequestDescription _arg0;
if ((0!=data.readInt())) {
_arg0 = com.stanfy.serverapi.request.RequestDescription.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.performRequest(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_registerCallback:
{
data.enforceInterface(DESCRIPTOR);
com.stanfy.app.service.ApiMethodCallback _arg0;
_arg0 = com.stanfy.app.service.ApiMethodCallback.Stub.asInterface(data.readStrongBinder());
boolean _arg1;
_arg1 = (0!=data.readInt());
this.registerCallback(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_removeCallback:
{
data.enforceInterface(DESCRIPTOR);
com.stanfy.app.service.ApiMethodCallback _arg0;
_arg0 = com.stanfy.app.service.ApiMethodCallback.Stub.asInterface(data.readStrongBinder());
this.removeCallback(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.stanfy.app.service.ApiMethods
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
public void performRequest(com.stanfy.serverapi.request.RequestDescription description) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((description!=null)) {
_data.writeInt(1);
description.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_performRequest, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void registerCallback(com.stanfy.app.service.ApiMethodCallback callback, boolean requiresModel) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
_data.writeInt(((requiresModel)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void removeCallback(com.stanfy.app.service.ApiMethodCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_removeCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_performRequest = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_removeCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void performRequest(com.stanfy.serverapi.request.RequestDescription description) throws android.os.RemoteException;
public void registerCallback(com.stanfy.app.service.ApiMethodCallback callback, boolean requiresModel) throws android.os.RemoteException;
public void removeCallback(com.stanfy.app.service.ApiMethodCallback callback) throws android.os.RemoteException;
}
