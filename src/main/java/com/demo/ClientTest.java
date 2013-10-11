package com.demo;

import java.io.IOException;
import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;

public class ClientTest {

	public static void main(String[] args) throws IOException {

		IRiakClient client = null;
		try {// 使用pbc方式连接，而不是http,在/etc/riak/app.config
			client = RiakFactory.pbcClient("127.0.0.1", 8087);
		} catch (RiakException e) {
			e.printStackTrace();
		}
		// 显示.
		System.out.println(client);
		Bucket myBucket = null;
		String bucketName = "userInfo";
		try {
			myBucket = client.fetchBucket(bucketName).execute();
			if (myBucket == null) {
				myBucket = client.createBucket(bucketName).execute();
			}
		} catch (RiakRetryFailedException e) {
			e.printStackTrace();
		}
		// ################保存数据 .
		UserInfo info = new UserInfo();
		info.setUid("001");
		info.setName("张三");
		info.setCity("北京");
		try {
			myBucket.store(info.getUid(), info).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ################查询数据.
		UserInfo fetchedUserInfo = null;
		try {
			fetchedUserInfo = myBucket.fetch("001", UserInfo.class).execute();
			System.out.println(fetchedUserInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ################修改数据.
		try {
			fetchedUserInfo = myBucket.fetch("001", UserInfo.class).execute();
			fetchedUserInfo.setName("李四");
			fetchedUserInfo.setNickName("老李");
			myBucket.store(info.getUid(), info).execute();
			// 保存 新数据
			fetchedUserInfo = myBucket.fetch("001", UserInfo.class).execute();
			System.out.println("新数据:" + fetchedUserInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ################删除数据.
		try {
			myBucket.delete("001").execute();
			fetchedUserInfo = myBucket.fetch("001", UserInfo.class).execute();
			System.out.println("删除收数据." + fetchedUserInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 关闭。
		client.shutdown();

	}

}
