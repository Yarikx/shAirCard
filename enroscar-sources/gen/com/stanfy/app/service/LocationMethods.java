/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/unlogic/work/projects/Android/shAirCard/enroscar-sources/src/com/stanfy/app/service/LocationMethods.aidl
 */
package com.stanfy.app.service;
public interface LocationMethods extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.stanfy.app.service.LocationMethods
{
private static final java.lang.String DESCRIPTOR = "com.stanfy.app.service.LocationMethods";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.stanfy.app.service.LocationMethods interface,
 * generating a proxy if needed.
 */
public static com.stanfy.app.service.LocationMethods asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.stanfy.app.service.LocationMethods))) {
return ((com.stanfy.app.service.LocationMethods)iin);
}
return new com.stanfy.app.service.LocationMethods.Stub.Proxy(obj);
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
case TRANSACTION_getLastLocation:
{
data.enforceInterface(DESCRIPTOR);
android.location.Location _result = this.getLastLocation();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_registerUpdateCallback:
{
data.enforceInterface(DESCRIPTOR);
com.stanfy.app.service.LocationUpdateCallback _arg0;
_arg0 = com.stanfy.app.service.LocationUpdateCallback.Stub.asInterface(data.readStrongBinder());
this.registerUpdateCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterUpdateCallback:
{
data.enforceInterface(DESCRIPTOR);
com.stanfy.app.service.LocationUpdateCallback _arg0;
_arg0 = com.stanfy.app.service.LocationUpdateCallback.Stub.asInterface(data.readStrongBinder());
this.unregisterUpdateCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_start:
{
data.enforceInterface(DESCRIPTOR);
this.start();
reply.writeNoException();
return true;
}
case TRANSACTION_stop:
{
data.enforceInterface(DESCRIPTOR);
this.stop();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.stanfy.app.service.LocationMethods
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
public android.location.Location getLastLocation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.location.Location _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getLastLocation, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.location.Location.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public void registerUpdateCallback(com.stanfy.app.service.LocationUpdateCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerUpdateCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void unregisterUpdateCallback(com.stanfy.app.service.LocationUpdateCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterUpdateCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void start() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_start, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void stop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stop, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getLastLocation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registerUpdateCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_unregisterUpdateCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_start = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_stop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
public android.location.Location getLastLocation() throws android.os.RemoteException;
public void registerUpdateCallback(com.stanfy.app.service.LocationUpdateCallback callback) throws android.os.RemoteException;
public void unregisterUpdateCallback(com.stanfy.app.service.LocationUpdateCallback callback) throws android.os.RemoteException;
public void start() throws android.os.RemoteException;
public void stop() throws android.os.RemoteException;
}
