/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/unlogic/work/projects/Android/shAirCard/enroscar-sources/src/com/stanfy/app/service/LocationUpdateCallback.aidl
 */
package com.stanfy.app.service;
public interface LocationUpdateCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.stanfy.app.service.LocationUpdateCallback
{
private static final java.lang.String DESCRIPTOR = "com.stanfy.app.service.LocationUpdateCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.stanfy.app.service.LocationUpdateCallback interface,
 * generating a proxy if needed.
 */
public static com.stanfy.app.service.LocationUpdateCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.stanfy.app.service.LocationUpdateCallback))) {
return ((com.stanfy.app.service.LocationUpdateCallback)iin);
}
return new com.stanfy.app.service.LocationUpdateCallback.Stub.Proxy(obj);
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
case TRANSACTION_updateLocation:
{
data.enforceInterface(DESCRIPTOR);
android.location.Location _arg0;
if ((0!=data.readInt())) {
_arg0 = android.location.Location.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.updateLocation(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.stanfy.app.service.LocationUpdateCallback
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
public void updateLocation(android.location.Location location) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((location!=null)) {
_data.writeInt(1);
location.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_updateLocation, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_updateLocation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void updateLocation(android.location.Location location) throws android.os.RemoteException;
}
