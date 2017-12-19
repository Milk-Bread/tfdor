import Vue from 'vue';
import Router from 'vue-router';
import Index from '@/components/Index';
import Member from '@/components/Member';
import Tab from '@/components/Tabbar';
import ShoppingCart from '@/components/ShoppingCart';
import InputTest from '@/components/index/InputTest';
import ButtonTest from '@/components/index/ButtonTest';
import New from '@/components/New';

Vue.use(Router);

export default new Router({
	routes: [{
		path: '/',
		redirect: '/Tab'
	},{
		path: '/Tab', // 一级路由
		name: 'Tab',
		component: Tab,
		children: [{
			path: 'Index', // 二级路由，首页
			name: 'Index',
			component: Index
		},{
			path: 'Member', // 二级路由,个人中心
			name: 'Member',
			component: Member
		},{
			path: 'ShoppingCart', // 二级路由，购物车
			name: 'ShoppingCart',
			component: ShoppingCart
		},{
			path: 'New', // 二级路由，新品
			name: 'New',
			component: New
		},{
			path: 'InputTest',
			name: 'InputTest',
			component: InputTest
		},{
			path: 'ButtonTest',
			name: 'ButtonTest',
			component: ButtonTest
		}]
	}]
});
