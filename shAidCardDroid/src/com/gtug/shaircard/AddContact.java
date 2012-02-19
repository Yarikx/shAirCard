public boolean addContact(String displayName, 
			String mobileNumber, 
			String email, 
			String company) {
		boolean flag = true;					
		ArrayList<ContentProviderOperation> ops = 
			new ArrayList<ContentProviderOperation>();

		ops.add(ContentProviderOperation.newInsert(
				ContactsContract.RawContacts.CONTENT_URI)
				.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
				.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
				.build()
				);

		//------------------------------------------------------ Names
		if(displayName != null)
		{           
			ops.add(ContentProviderOperation.newInsert(
				ContactsContract.Data.CONTENT_URI)              
				.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				.withValue(ContactsContract.Data.MIMETYPE,
						ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
						.withValue(
								ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,     
								displayName).build());
		} 

		//------------------------------------------------------ Mobile Number                      
		if(mobileNumber != null)
		{
			ops.add(ContentProviderOperation.
				newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				.withValue(ContactsContract.Data.MIMETYPE,
						ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
				.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, mobileNumber)
				.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, 
						ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
				.build());
		}
		//------------------------------------------------------ Email
		if(email != null)
		{
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				.withValue(ContactsContract.Data.MIMETYPE,
						ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
				.withValue(ContactsContract.CommonDataKinds.Email.DATA, email)
				.withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
				.build());
		}

		//------------------------------------------------------ Organization
		if(!company.equals("")||company!=null )
		{
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				.withValue(ContactsContract.Data.MIMETYPE,
						ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
						.withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
						.withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
						.build());
		}
		//--------------------------------------Photo
		/*File f = new File("123.png");
		
		//byte bytes[] = new byte[1024];
	
		if(true)
		{
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				.withValue(ContactsContract.Data.MIMETYPE,
						ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
						.withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, getBytesFromFile(f))
						
						.build());
		}*/

		// Asking the Contact provider to create a new contact                  
		try 
		{
			getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		}	 
		catch (Exception e) 
		{               
			e.printStackTrace();
			flag = false;
			
		}	
		return flag;
	}	