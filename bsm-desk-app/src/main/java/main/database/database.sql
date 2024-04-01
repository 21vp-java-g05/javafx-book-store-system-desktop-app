PGDMP      /                |         
   Book_store    16.2    16.2 V    X           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            Y           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            Z           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            [           1262    34319 
   Book_store    DATABASE     �   CREATE DATABASE "Book_store" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Vietnamese_Vietnam.1258';
    DROP DATABASE "Book_store";
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            \           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    34438    imports_book    TABLE     �   CREATE TABLE public.imports_book (
    imports_id integer NOT NULL,
    book_id integer NOT NULL,
    quantity integer NOT NULL,
    import_price real NOT NULL,
    remaining integer NOT NULL
);
     DROP TABLE public.imports_book;
       public         heap    postgres    false    4            �            1255    34468    defaultremaining()    FUNCTION     �   CREATE FUNCTION public.defaultremaining() RETURNS integer
    LANGUAGE sql
    RETURN (SELECT imports_book.quantity FROM public.imports_book);
 )   DROP FUNCTION public.defaultremaining();
       public          postgres    false    232    4            �            1259    34383    account    TABLE     2  CREATE TABLE public.account (
    id integer NOT NULL,
    fullname character varying(255) NOT NULL,
    mail character varying(321) NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role integer NOT NULL,
    status boolean DEFAULT true NOT NULL
);
    DROP TABLE public.account;
       public         heap    postgres    false    4            �            1259    34382    account_id_seq    SEQUENCE     �   CREATE SEQUENCE public.account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.account_id_seq;
       public          postgres    false    4    226            ]           0    0    account_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.account_id_seq OWNED BY public.account.id;
          public          postgres    false    225            �            1259    34341    author    TABLE     �   CREATE TABLE public.author (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    status boolean DEFAULT true NOT NULL
);
    DROP TABLE public.author;
       public         heap    postgres    false    4            �            1259    34340    author_id_seq    SEQUENCE     �   CREATE SEQUENCE public.author_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.author_id_seq;
       public          postgres    false    220    4            ^           0    0    author_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.author_id_seq OWNED BY public.author.id;
          public          postgres    false    219            �            1259    34351    book    TABLE     +  CREATE TABLE public.book (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    isbn character(13) NOT NULL,
    language character varying(255),
    number_of_pages integer,
    publisher integer NOT NULL,
    author integer NOT NULL,
    status boolean DEFAULT true NOT NULL
);
    DROP TABLE public.book;
       public         heap    postgres    false    4            �            1259    34350    book_id_seq    SEQUENCE     �   CREATE SEQUENCE public.book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.book_id_seq;
       public          postgres    false    222    4            _           0    0    book_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;
          public          postgres    false    221            �            1259    34321    category    TABLE     �   CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    status boolean DEFAULT true NOT NULL
);
    DROP TABLE public.category;
       public         heap    postgres    false    4            �            1259    34423    category_book    TABLE     f   CREATE TABLE public.category_book (
    category_id integer NOT NULL,
    book_id integer NOT NULL
);
 !   DROP TABLE public.category_book;
       public         heap    postgres    false    4            �            1259    34320    category_id_seq    SEQUENCE     �   CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.category_id_seq;
       public          postgres    false    4    216            `           0    0    category_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;
          public          postgres    false    215            �            1259    34371    customer    TABLE     �   CREATE TABLE public.customer (
    id integer NOT NULL,
    fullname character varying(255) NOT NULL,
    mail character varying(321) NOT NULL,
    gender boolean NOT NULL,
    status boolean DEFAULT true NOT NULL
);
    DROP TABLE public.customer;
       public         heap    postgres    false    4            �            1259    34370    customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.customer_id_seq;
       public          postgres    false    4    224            a           0    0    customer_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;
          public          postgres    false    223            �            1259    34395    imports    TABLE     �   CREATE TABLE public.imports (
    id integer NOT NULL,
    import_time timestamp without time zone NOT NULL,
    employee integer NOT NULL,
    total_cost real NOT NULL
);
    DROP TABLE public.imports;
       public         heap    postgres    false    4            �            1259    34394    imports_id_seq    SEQUENCE     �   CREATE SEQUENCE public.imports_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.imports_id_seq;
       public          postgres    false    4    228            b           0    0    imports_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.imports_id_seq OWNED BY public.imports.id;
          public          postgres    false    227            �            1259    34407    orders    TABLE     �   CREATE TABLE public.orders (
    id integer NOT NULL,
    order_time timestamp without time zone NOT NULL,
    employee integer NOT NULL,
    customer integer,
    sale_price real NOT NULL
);
    DROP TABLE public.orders;
       public         heap    postgres    false    4            �            1259    34453    orders_book    TABLE     �   CREATE TABLE public.orders_book (
    orders_id integer NOT NULL,
    book_id integer NOT NULL,
    quantity integer NOT NULL,
    price real NOT NULL
);
    DROP TABLE public.orders_book;
       public         heap    postgres    false    4            �            1259    34406    orders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public          postgres    false    230    4            c           0    0    orders_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;
          public          postgres    false    229            �            1259    34331 	   publisher    TABLE     �   CREATE TABLE public.publisher (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    status boolean DEFAULT true NOT NULL
);
    DROP TABLE public.publisher;
       public         heap    postgres    false    4            �            1259    34330    publisher_id_seq    SEQUENCE     �   CREATE SEQUENCE public.publisher_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.publisher_id_seq;
       public          postgres    false    4    218            d           0    0    publisher_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.publisher_id_seq OWNED BY public.publisher.id;
          public          postgres    false    217            �           2604    34386 
   account id    DEFAULT     h   ALTER TABLE ONLY public.account ALTER COLUMN id SET DEFAULT nextval('public.account_id_seq'::regclass);
 9   ALTER TABLE public.account ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225    226            �           2604    34344 	   author id    DEFAULT     f   ALTER TABLE ONLY public.author ALTER COLUMN id SET DEFAULT nextval('public.author_id_seq'::regclass);
 8   ALTER TABLE public.author ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    34354    book id    DEFAULT     b   ALTER TABLE ONLY public.book ALTER COLUMN id SET DEFAULT nextval('public.book_id_seq'::regclass);
 6   ALTER TABLE public.book ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    34324    category id    DEFAULT     j   ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);
 :   ALTER TABLE public.category ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    34374    customer id    DEFAULT     j   ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);
 :   ALTER TABLE public.customer ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223    224            �           2604    34398 
   imports id    DEFAULT     h   ALTER TABLE ONLY public.imports ALTER COLUMN id SET DEFAULT nextval('public.imports_id_seq'::regclass);
 9   ALTER TABLE public.imports ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    228    228            �           2604    34469    imports_book remaining    DEFAULT     c   ALTER TABLE ONLY public.imports_book ALTER COLUMN remaining SET DEFAULT public.defaultremaining();
 E   ALTER TABLE public.imports_book ALTER COLUMN remaining DROP DEFAULT;
       public          postgres    false    234    232            �           2604    34410 	   orders id    DEFAULT     f   ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 8   ALTER TABLE public.orders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    229    230    230            �           2604    34334    publisher id    DEFAULT     l   ALTER TABLE ONLY public.publisher ALTER COLUMN id SET DEFAULT nextval('public.publisher_id_seq'::regclass);
 ;   ALTER TABLE public.publisher ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            N          0    34383    account 
   TABLE DATA           W   COPY public.account (id, fullname, mail, username, password, role, status) FROM stdin;
    public          postgres    false    226   �a       H          0    34341    author 
   TABLE DATA           ?   COPY public.author (id, name, description, status) FROM stdin;
    public          postgres    false    220   if       J          0    34351    book 
   TABLE DATA           e   COPY public.book (id, title, isbn, language, number_of_pages, publisher, author, status) FROM stdin;
    public          postgres    false    222   �o       D          0    34321    category 
   TABLE DATA           A   COPY public.category (id, name, description, status) FROM stdin;
    public          postgres    false    216   �s       S          0    34423    category_book 
   TABLE DATA           =   COPY public.category_book (category_id, book_id) FROM stdin;
    public          postgres    false    231   �z       L          0    34371    customer 
   TABLE DATA           F   COPY public.customer (id, fullname, mail, gender, status) FROM stdin;
    public          postgres    false    224   A{       P          0    34395    imports 
   TABLE DATA           H   COPY public.imports (id, import_time, employee, total_cost) FROM stdin;
    public          postgres    false    228   �}       T          0    34438    imports_book 
   TABLE DATA           ^   COPY public.imports_book (imports_id, book_id, quantity, import_price, remaining) FROM stdin;
    public          postgres    false    232   �}       R          0    34407    orders 
   TABLE DATA           P   COPY public.orders (id, order_time, employee, customer, sale_price) FROM stdin;
    public          postgres    false    230   �}       U          0    34453    orders_book 
   TABLE DATA           J   COPY public.orders_book (orders_id, book_id, quantity, price) FROM stdin;
    public          postgres    false    233   �}       F          0    34331 	   publisher 
   TABLE DATA           B   COPY public.publisher (id, name, description, status) FROM stdin;
    public          postgres    false    218   ~       e           0    0    account_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.account_id_seq', 20, true);
          public          postgres    false    225            f           0    0    author_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.author_id_seq', 24, true);
          public          postgres    false    219            g           0    0    book_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.book_id_seq', 30, true);
          public          postgres    false    221            h           0    0    category_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.category_id_seq', 20, true);
          public          postgres    false    215            i           0    0    customer_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.customer_id_seq', 20, true);
          public          postgres    false    223            j           0    0    imports_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.imports_id_seq', 1, false);
          public          postgres    false    227            k           0    0    orders_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.orders_id_seq', 1, false);
          public          postgres    false    229            l           0    0    publisher_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.publisher_id_seq', 10, true);
          public          postgres    false    217            �           2606    34393    account account_mail_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_mail_key UNIQUE (mail);
 B   ALTER TABLE ONLY public.account DROP CONSTRAINT account_mail_key;
       public            postgres    false    226            �           2606    34391    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    226            �           2606    34349    author author_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.author DROP CONSTRAINT author_pkey;
       public            postgres    false    220            �           2606    34359    book book_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public            postgres    false    222            �           2606    34329    category category_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            postgres    false    216            �           2606    34381    customer customer_mail_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_mail_key UNIQUE (mail);
 D   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_mail_key;
       public            postgres    false    224            �           2606    34379    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    224            �           2606    34400    imports imports_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.imports
    ADD CONSTRAINT imports_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.imports DROP CONSTRAINT imports_pkey;
       public            postgres    false    228            �           2606    34412    orders orders_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    230            �           2606    34427    category_book pk_category_book 
   CONSTRAINT     n   ALTER TABLE ONLY public.category_book
    ADD CONSTRAINT pk_category_book PRIMARY KEY (category_id, book_id);
 H   ALTER TABLE ONLY public.category_book DROP CONSTRAINT pk_category_book;
       public            postgres    false    231    231            �           2606    34442    imports_book pk_import_book 
   CONSTRAINT     j   ALTER TABLE ONLY public.imports_book
    ADD CONSTRAINT pk_import_book PRIMARY KEY (imports_id, book_id);
 E   ALTER TABLE ONLY public.imports_book DROP CONSTRAINT pk_import_book;
       public            postgres    false    232    232            �           2606    34457    orders_book pk_orders_book 
   CONSTRAINT     h   ALTER TABLE ONLY public.orders_book
    ADD CONSTRAINT pk_orders_book PRIMARY KEY (orders_id, book_id);
 D   ALTER TABLE ONLY public.orders_book DROP CONSTRAINT pk_orders_book;
       public            postgres    false    233    233            �           2606    34339    publisher publisher_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.publisher
    ADD CONSTRAINT publisher_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.publisher DROP CONSTRAINT publisher_pkey;
       public            postgres    false    218            �           2606    34365    book fk_book_author    FK CONSTRAINT     r   ALTER TABLE ONLY public.book
    ADD CONSTRAINT fk_book_author FOREIGN KEY (author) REFERENCES public.author(id);
 =   ALTER TABLE ONLY public.book DROP CONSTRAINT fk_book_author;
       public          postgres    false    222    220    4756            �           2606    34360    book fk_book_publisher    FK CONSTRAINT     {   ALTER TABLE ONLY public.book
    ADD CONSTRAINT fk_book_publisher FOREIGN KEY (publisher) REFERENCES public.publisher(id);
 @   ALTER TABLE ONLY public.book DROP CONSTRAINT fk_book_publisher;
       public          postgres    false    218    222    4754            �           2606    34433 #   category_book fk_category_book_book    FK CONSTRAINT     �   ALTER TABLE ONLY public.category_book
    ADD CONSTRAINT fk_category_book_book FOREIGN KEY (book_id) REFERENCES public.book(id);
 M   ALTER TABLE ONLY public.category_book DROP CONSTRAINT fk_category_book_book;
       public          postgres    false    4758    222    231            �           2606    34428 '   category_book fk_category_book_category    FK CONSTRAINT     �   ALTER TABLE ONLY public.category_book
    ADD CONSTRAINT fk_category_book_category FOREIGN KEY (category_id) REFERENCES public.category(id);
 Q   ALTER TABLE ONLY public.category_book DROP CONSTRAINT fk_category_book_category;
       public          postgres    false    4752    231    216            �           2606    34448     imports_book fk_import_book_book    FK CONSTRAINT     ~   ALTER TABLE ONLY public.imports_book
    ADD CONSTRAINT fk_import_book_book FOREIGN KEY (book_id) REFERENCES public.book(id);
 J   ALTER TABLE ONLY public.imports_book DROP CONSTRAINT fk_import_book_book;
       public          postgres    false    4758    222    232            �           2606    34443 "   imports_book fk_import_book_import    FK CONSTRAINT     �   ALTER TABLE ONLY public.imports_book
    ADD CONSTRAINT fk_import_book_import FOREIGN KEY (imports_id) REFERENCES public.imports(id);
 L   ALTER TABLE ONLY public.imports_book DROP CONSTRAINT fk_import_book_import;
       public          postgres    false    232    4768    228            �           2606    34401    imports fk_imports_account    FK CONSTRAINT     |   ALTER TABLE ONLY public.imports
    ADD CONSTRAINT fk_imports_account FOREIGN KEY (employee) REFERENCES public.account(id);
 D   ALTER TABLE ONLY public.imports DROP CONSTRAINT fk_imports_account;
       public          postgres    false    228    226    4766            �           2606    34413    orders fk_orders_account    FK CONSTRAINT     z   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_orders_account FOREIGN KEY (employee) REFERENCES public.account(id);
 B   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk_orders_account;
       public          postgres    false    226    4766    230            �           2606    34463    orders_book fk_orders_book_book    FK CONSTRAINT     }   ALTER TABLE ONLY public.orders_book
    ADD CONSTRAINT fk_orders_book_book FOREIGN KEY (book_id) REFERENCES public.book(id);
 I   ALTER TABLE ONLY public.orders_book DROP CONSTRAINT fk_orders_book_book;
       public          postgres    false    4758    222    233            �           2606    34458 !   orders_book fk_orders_book_import    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders_book
    ADD CONSTRAINT fk_orders_book_import FOREIGN KEY (orders_id) REFERENCES public.orders(id);
 K   ALTER TABLE ONLY public.orders_book DROP CONSTRAINT fk_orders_book_import;
       public          postgres    false    233    4770    230            �           2606    34418    orders fk_orders_customer    FK CONSTRAINT     |   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_orders_customer FOREIGN KEY (customer) REFERENCES public.customer(id);
 C   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk_orders_customer;
       public          postgres    false    224    4762    230            N   �  x�E�ێ�J��{��' ��;Ȩ#xD�섴�H#4Мħ��?w�k����V�`�Q��q��܁䑪�U�-�4�1<�:�#���T+�bk�b�0��*�~f�� ���*Z�d w QP�q�4e_)A�~FI�&��~����$ �"�⫶�7^����S'�Մ3E0���ȉcT��h�!Z�༛�'U�k�3��y��y|�)3�4[�V)���}%0�������{X�cw`A�_wxG�<���)�d{�1����5<�[������®��20!�C�JƄ������ˢ4�J�<XX�i��|Y;h��[��U� �3�E~T���-V�&)a�4��e7e��y�E=|k��V�y��	x�r)�_N=�7T�>- �BA�hۃ�Y���-�����}%�28s����s�@��/�S�i[^�u�{j��1B�&8~����/K�ď�y���
|'`*��@�_On.5�������oDږ
���+���1+Ja;�;�����n��&��9m������Z�@��|Ͼ�S-�����=y�=!����D����~����;.���P��e[�|5��ꈞ��;�A�e^��+ViHH�tE��v�Z�eaLn��٢L)�d�>��Qm��Q�A�s������Zك� tt�C6D��5M��}�*۠[�K�G4{:��k��anZ�h�D\mq�?�����O/�I�@�����#I�BZ�7���l��n�9Ύ��F�����ÙI{��+�%����w���lS����F�S�1�H|�_Oh�-_�3߮��U�9�< 뉟ћ$������[�*Ɛ�8�x��/��rw ZP�} �V�N�����SÉ��	L�U.�&����Î�J��1�d�����~4E�*o���"e7�e�[T$�I�.�5�W�U(V7-lg��_C��߅p�5��߁X�Uq�,��"ʦ���,]ߠ3li�*S��������QMN#�C�X�}�3ǐ|�HQi���!�~ ���dŭL���B���lr���lY��U���1ef����^w���m�1E^��-=�������9��;\͝;X$%���_+��\�����o܍�Li��^��������ua[o�oC�Pa}��r �_��zB����C߅�د�����>�      H   h	  x��X�r�6}��oڭ�"_��Sjt���I�V��ĐȀ �3���~�~E~lO7@�ƚ�<đe\�O�>}�����͉xt�m]���ʺ�K�El��(�ă�Qy��*���GAl�_�FQ�.굌��6F;��h��������he�Ki�ű�����Z�[�%.�Uht7�뽬Ռ�Yz�ԥ:)⛷�ST]������86P|8�q�#t�҇NـhG��[Dh�Vy��@�A�|ږ��h�3�~j���3�)� �4�fBIo�8�2@QG��f�_��?r��Z9_+�x�x"���0F �r��P�.�R�(ÐQs���i��ӕ�Hf��`��ᯌ��"*��: x�ײ尞�,-�-p[��ϵ�6z&�T��E�cA�����&�FzY�@��w8�ggV�ZA�-%��B��R�NI�}2��A� �Ж[��G\B1��bF-#�@��2z�[	�!KZ_+K*��l�x��Usb�'��!6��d����yG�|(�5B���:D���~�����+P!�D3�`\�*U�L�n���������B܎��[��r�d�������+���G����O~9�/��"W��á��:���͝q1�۪�yIh�����ܯ4E&W����(إ�Q�w���	~�p�.�z�ߦ����3��߽�UkT�s/��˕AyB�R�!xܾS 2@
�Vtzf�Z��o%Z��Sn+��Zz�<npYq3)q�B�\G�$��B_=��٪��"BJ�h�~j):�	��<������� �&�P)B��L����D�6�gs�V(�8����xv�b��,
r��#95$m|�Q�-�x�z��7ڲ��f�f$�g��w	�QQ����L�%�!� tQ� 7���@+��k�Qe!�nP"?�r�H�/���w���ѫ-r�P$��!� ����W�/�mEb�w�o�	��c���3jzӇ�*T&(�KSh󭌻�-�_t>IC��:�s�Cq5�
'^8,P���c�(���1#v����3�T�n����G���뺡��I�K�u����S�{hV�hմ�$2��8�T7���l�֭9���7�k�PR�e1']�>���V��n�MH�)��Ɨ�{_� /��62��4��`q�y^����p�4&z9�\�\%	�呖�����yў���-n������+Xk\����b��\s?!I�d����@����q�Z|,�W�LŃ�yϭ��8�?�5%6�K�N���RhI6j��R����Gv�F? �Ȥq њ�N*�4
1��ۘ�拁�A,cdOJ���n�(BƉ�o���]��(M�T���p�.������umRG�/����4h�=��������_H��=���
�;h�S_<{l����]uPeYfbAJۗ�Ջ2�"@��f� %��,A�yOa����R�}��4�.m��7Px[�^:I�i�J�����*Կ�*����]�����'�Љqn�D�ȼLR3���p5��%U��@dFQǇ1v��{(C�cq�VD-ą.W���4����o�$�M���%o9�:��Z�,'�$���f7N��op�Z�u�n��[�˜����2'�-8�ش'����)���K91�+�$5M�?z@ƌg�MSc����Y~�D�r�tL�e�&�t�����U2�j�A��>��
�Lѣ�
�7qyٳkœ�d,�{�_�"4S�Ϯ�g��t�T\���/;��5�c*�84X;]N\�,�[1'���f��jv-cX���HQ�v&���K�M ^`o;�\���F�ڠ�c3�"�,�$�á�[�7T,�sr6���w�{�S��d��^R���~ 4�o�O��~mp����!���+��ʗ�;���Fn{'>�E��rH�̃�P�)=l�,;�$�R�(=�6t�T��\�\��<��������Ce݌yL/.d��G�tm�ߗ�_�!�؁� �^pn��ܐ���	�r�ƀ�x���<l`��Z�Z��x/���Pe�����;]�L1ti *@O��_�_d���~+K�R�V��\��_����'h�"!�`]L�E�V�e�ǃ8��o�� ���
sh��&-@{�>�L`��򧘽2_���������H�qNt��Q���ѹ�fU�T�����U�^?�Ի�^ijO���>Ќ蠊��Նa����9�ŵ���s6T6���盝�N��Kz1>�gHǬ?3�8�Y�II(�sk��5~� ���y$�nE��Y`N�}��j�<g=h�iz������6����v�}�got���I2��Ǥ�����W+2�{��<�l��p����N&2��F	���͛7�ֶ2�      J   �  x�u�Mo�F���_�[n��~�h;�j'���0��Z�XS\�\�U}ߥ�(R���ٙy�y��*�X�K!�J)�4���F�XKbDi$=lc{^Ҧ���XB�X�8���B��_qz�G�5GQE�y����-x�bHq��Z#=[���t�Mc�#Y/I!J������0m�5�:ϥ}H�HNi�g�t��^��D�֊4�4fm�czË�Y���@RJg��Z�t�0-s�x�Z&�(����.��,�T�;��>��Yk���8���K�c{�^���^d4��o%�6N��y�:����k_ga,�h��C��7��g�P��)O�s ��"�(MG��<��]�{LcO�U��[�=-���:�Q�W��O�o���\%��C*KI{��ZX��N��6�:�h�~���^��^�2?�H���N0ԠO�@@�^�i�>"r�s_��)N�ϰ����	M���&�:��+���i��؞a19���E:��9�ޢ���ҁnB�����Ax�9MV�J	/y����$6�6t;�>�a���)�X����j�1����0oK�$ԕs��Vt����[`WV8��|_��]e�+-��1L�j1�����'_��]�����elO�9�wi$x�uW�.�a���+%��6a����X@Fv�Җ� ],C�@Zx�+(R�IW�nR�a^�$�0�3�x����:l��y�cJs�®��"p�}k\X����L������_�ԁ��b����~�ì��lH�+=$�#]��?�C�k"+ R�yw0@�:�t�W�R���>�}����dEDj��>헹�gy��2�`F��=̇��(�5��hz&��J��ʖ����-.\��d��(�����8��!A�\���w�y�+<����}ܔPR��1�+�AWz\���׻�>���c��>N�Sz���V=�Z1�-�z%,sV���Ѽ@[J��f�owaݙ�ج���i�����Z      D   �  x��W�r7<�� �,?�-˱��+R���wa��%�����A�d�I`�==���}�#��H�>�S��O*x��h���Q�!z��Aя��Q.�()�kU۔����CR{�[����dSa���ē�aG.]�}K��־�(�臱�:�Vz�-�fE;���u���W/���慨�)S��n�N}���p�IO��HY��sKj���g�6�����t&D��M*��&��Z�L�c�EI�v�{����u��H��O����!�"G����y��Z�\�����{Y��(�'TS����u��L���@���J�k�"6u��h���
\^;!���I6Q#Y������ �I��(�K%���Щ�R����D^U��-P�~����GT �!�5!܌M�@E�0�Q]��uJ1���E�t	\��,g'�9|RR
@�h�S�m�y�c�O�װ�;c	���I�^W���|�%�G�4���ѻ�D�RhK��K�{']e�&x����'8]h[�\n'�yv9�h�����'���B3� ��ِ��pj�Gv\D[w� �̭�1��a('Ӑz�8�7՟��Q���eu���C3u���'���T������9�Q�h�ӆP��F&�qp��]<�t5��Yo R�JmP����نs�PZL&���(�^Q�:��/��@����$é����Ѵ\>��T��C�i^Ћ�Py��0=aFZ���QOD=V@.����������%D�G�}8�߇ܲY� ��T�+�a�\��%ڛe|[��S����Y�Q�Cx�`Cu�2�~�j!�F�@ Rd8)��RN`��l=h�oa���/ $4������f�a�y����QX�5�k�M`����<�ߑ��U�衷��ù��0�4�C��1��Wpg�k����� �^��cxܑ��T�ġC�ꂲv /q5���`'X;�	^�I]�<~k��Y���9������-��5�,�e?�OTZ�	�����<�v���oj=^��
�I�
c�
@�v�m\*.�#9�VC�-@i�%��_Q9#z��30��8_���F.0��"N�i�����aCGQv�o+I���ڳ���/={q�
gH>dP��a;��Oϩr�{��s���"±���3�<�[�a�4��2����de�iu=�w��M\����NF��e5���!?W�P. ��h])emM�#aʭ���@������y�]b�IFr/�"��I;� 	�W|d���	gy>�6D\��$�W����%���CE��-�����)�9/Z��g�R���m@��;[�B�p;߾��ٺK�5V�"��(k+�u�U1�@���f�8�i���2��.�̚5t���y�����
�Z�=��`Q��,%o�T_Q �y|�	c���F:��EvS�?S>|��� 47�<?P0�BP��j����zY�<jXcyvSCu�E���o$���#�#ko����T��D���"��\���Ş��E"�s=��'�+���܎i���-D����G����"�0�\����x����
� �sВ���o��(�%;! �i.���d��Q������4x�dȒ��WS��o;��]� ����g�_IR䭎'3s3�%����Pv�f�l�8��I��V���Y��.�w+�ŲI޿ϫ���� +�.�M�A�;v�L5��t�\�끝.Y�3>���<�J���R���d��
��p�U����׳���� �iw      S   c   x���@c(�s���������(.�������������b&"�D�)�Yb�-�9�+�y�9C����T73{�Մ��49�_������?v$I      L   B  x�U�Kn�0���)xBo�;�)�6�Ӣ-�Mbd�%&)S����H9iw�7��<�oR\�Н��J<P�-��Ogæ^��H�&!��@O��j�H�"�g�f�Z!��=%���$�~D�7��i�����*6=������-�ҽ]oߘN�$�B��d������R��j��4�Gk�gg�'W�0hE�Z�A�EeRԺ?�z6�p9J�CO@��xD3{�+�I^������<7~Ry�Y"�/0�+0�qgq�rz*���Itʇm��WPtw6� <@UƮ��E
�`@�GK�p�ߢ��9�n/�S�Z�	���br�{�f��U�E�q�Ԫ�2�&�Mh:N�'l���mܐ��*���T�+6��kY)� ������6K�Z ���3WȄc/��Z���L�"���M�z�����,(t��2���y�l��)�#�
��"�)�qZ�_UQ�|W�4;���k��|������ޏ,p�i��8\ؤ���>���z��@JP+�@EQd�S%���n�/w-��]��k�]�V<�U�'��g�&��F��5:^���*�/��l6*�S�      P      x������ � �      T      x������ � �      R      x������ � �      U      x������ � �      F   k  x�}U;��6�y�b�T'O�wZ�$s.�q�
7i p)�<$˿>�<��͝ �����s1��a.�.q��kʑ�'9�x�u�$��dM,r2EO��/d�H�
{�4�-<�pb��̦PI��R��f��@�̜��s�0��>�n~5���'���/�&�c�]�dyk
�ZlP�*IFA�
��dj�s	IxEe��2�L{6�\(L�����풤.q�}]�u1��R��_2�&�[�Y(ƣgOSH$%[���"��rɅ�*_��>��#MlJM
��$F�](]��Q�����c��IƖ��;2ǫҰB��x���$�(�T�ix?˒!���!��wW%�q��lX)��{E���P����|�d�{㖁+ȉ�����,e&�lm��A{��R�9]h��A_H���t�����~/p�	����ڷ��_�P��.�p
Ǘ��$_�ޔ��q�D��G��~��t���9�`�m��*��� ���n,J��{-���j毟|�+),�ɹ���v|�	����GeN�>ؚ���-��6XL��]|l���c�qk�
�)�a�^�U��:�эD�?U��v����I�1����p���� �5�s�^S4£�޸p��4]'�,��Z��/������Ik��'���O?U]�p�-�S
���l'f-�������l�s D1�C3�<!G�[/����v�>��Ҡ�������m
��郯�|��=�&5Z7x'1�Ϙ-7���"R�� q� ��p�Д/AR�����6�{Xh�"��#����U�OQ�[�NA,�|`:Ј�Q�|ڌ�>�Z��'�E[������8I,�B81�.W��N�ne�難����3�~     