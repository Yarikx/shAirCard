/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/unlogic/work/projects/Android/shAirCard/enroscar-sources/src/com/stanfy/audio/StreamingPlaybackListener.aidl
 */
package com.stanfy.audio;
public interface StreamingPlaybackListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.stanfy.audio.StreamingPlaybackListener
{
private static final java.lang.String DESCRIPTOR = "com.stanfy.audio.StreamingPlaybackListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.stanfy.audio.StreamingPlaybackListener interface,
 * generating a proxy if needed.
 */
public static com.stanfy.audio.StreamingPlaybackListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.stanfy.audio.StreamingPlaybackListener))) {
return ((com.stanfy.audio.StreamingPlaybackListener)iin);
}
return new com.stanfy.audio.StreamingPlaybackListener.Stub.Proxy(obj);
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
case TRANSACTION_onAudioInfo:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
this.onAudioInfo(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_onVolumeChanged:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.onVolumeChanged(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onPreparing:
{
data.enforceInterface(DESCRIPTOR);
this.onPreparing();
reply.writeNoException();
return true;
}
case TRANSACTION_onPrepared:
{
data.enforceInterface(DESCRIPTOR);
this.onPrepared();
reply.writeNoException();
return true;
}
case TRANSACTION_onError:
{
data.enforceInterface(DESCRIPTOR);
this.onError();
reply.writeNoException();
return true;
}
case TRANSACTION_onCompleted:
{
data.enforceInterface(DESCRIPTOR);
this.onCompleted();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.stanfy.audio.StreamingPlaybackListener
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
public void onAudioInfo(java.lang.String title, java.lang.String author, java.lang.String album) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(title);
_data.writeString(author);
_data.writeString(album);
mRemote.transact(Stub.TRANSACTION_onAudioInfo, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void onVolumeChanged(int volume) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(volume);
mRemote.transact(Stub.TRANSACTION_onVolumeChanged, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void onPreparing() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onPreparing, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void onPrepared() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onPrepared, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void onError() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void onCompleted() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onCompleted, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onAudioInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onVolumeChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onPreparing = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_onPrepared = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_onError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_onCompleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
}
public void onAudioInfo(java.lang.String title, java.lang.String author, java.lang.String album) throws android.os.RemoteException;
public void onVolumeChanged(int volume) throws android.os.RemoteException;
public void onPreparing() throws android.os.RemoteException;
public void onPrepared() throws android.os.RemoteException;
public void onError() throws android.os.RemoteException;
public void onCompleted() throws android.os.RemoteException;
}
