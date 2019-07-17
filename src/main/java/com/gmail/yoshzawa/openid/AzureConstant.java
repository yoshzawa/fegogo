package com.gmail.yoshzawa.openid;

public interface AzureConstant {
	
	// https://docs.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-implicit-grant-flow
	// https://portal.azure.com/#blade/Microsoft_AAD_RegisteredApps/ApplicationsListBlade
	// ID トークンを正しく要求するには、Azure portal の [アプリの登録] ページで、 [暗黙の付与] セクションの [アクセス トークン] と
	// [ID トークン] を選択して、アプリ登録の暗黙的な許可フローを適切に有効にする必要があります。 
	// それが有効でない場合は、unsupported_response エラー The provided value for the input parameter 
	// 'response_type' is not allowed for this client.Expected value is 'code' 
	// (入力パラメーター 'response_type' に入力された値はこのクライアントで許可されません。入力できる値は 'code' です。) が返されます

	
//	public static final String AzureAppName="fegogo.fivepro.xyz";
	public static final String AzureAppId="42ba67e2-5751-4ad0-b06c-216f7168a2c6";
}
