<template>
	<div class="">
		<mt-range v-model="rangeValue" :min="5" :max="100" :step="5" :bar-height="1" />
		<mt-button type="default" @click="show">default</mt-button><br />
		<mt-button type="primary" @click="show">primary</mt-button><br />
		<mt-button type="danger" @click="show">danger</mt-button>

		<mt-actionsheet :actions="actions" v-model="sheetVisible" />

		<mt-cell title="标题文字"></mt-cell>
		<mt-cell title="标题文字" value="说明文字"></mt-cell>

		<hr />

		<mt-field label="用户名" placeholder="请输入用户名" v-model="username"></mt-field>
		<mt-field label="邮箱" placeholder="请输入邮箱" type="email" v-model="email"></mt-field>
		<mt-field label="密码" placeholder="请输入密码" type="password" v-model="password"></mt-field>
		<mt-field label="手机号" placeholder="请输入手机号" type="tel" v-model="phone"></mt-field>
		<mt-field label="网站" placeholder="请输入网址" type="url" v-model="website"></mt-field>
		<mt-field label="数字" placeholder="请输入数字" type="number" v-model="number"></mt-field>
		<mt-field label="生日" placeholder="请输入生日" type="date" v-model="birthday"></mt-field>
		<mt-field label="自我介绍" placeholder="自我介绍" type="textarea" rows="4" v-modal="introduction"></mt-field>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				rangeValue: 10,
				actions: [{
					name: '确定',
					method: 'goBack'
				}, {
					name: '返回上一步',
					method: 'goBack'
				}],
				sheetVisible: false
			};
		},
		methods: {
			goBack: function() {
				this.$router.go(-1);
			},
			show: function() {
				this.sheetVisible = true;
			}
		}
	};
</script>

<style>

</style>