<template>
	<div>
		<header class="mint-header">
			<div class="mint-header-button is-left">
			</div>
			<h1 class="mint-header-title">{{title}}</h1>
			<div class="mint-header-button is-right">
				<button class="mint-button mint-button--default mint-button--normal">
						<span class="mint-button-icon"><i class="mintui mintui-more"></i></span>
						<label class="mint-button-text"></label>
					</button>
			</div>
		</header>
		<mt-swipe :auto="4000" style="width: 100%;height: 125px;">
			<mt-swipe-item><img src="../assets/images/1.jpeg" width="100%" /></mt-swipe-item>
			<mt-swipe-item><img src="../assets/images/2.jpeg" width="100%" /></mt-swipe-item>
			<mt-swipe-item><img src="../assets/images/3.jpeg" width="100%" /></mt-swipe-item>
			<mt-swipe-item><img src="../assets/images/4.jpg" width="100%" /></mt-swipe-item>
		</mt-swipe>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				title: '首页'
			};
		},
		methods: {
			goBack: function() {
				this.$router.go(-1);
			}
		}
	};
</script>

<style>

</style>