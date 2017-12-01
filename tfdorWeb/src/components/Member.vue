<template>
	<div>
		<header class="mint-header">
			<div class="mint-header-button">
			</div>
			<h1 class="mint-header-title">{{title}}</h1>
			<div class="mint-header-button is-right">
				<button class="mint-button mint-button--default mint-button--normal">
						<span class="mint-button-icon"><i class="mintui mintui-more"></i></span>
						<label class="mint-button-text"></label>
					</button>
			</div>
		</header>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				title: '我的'
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