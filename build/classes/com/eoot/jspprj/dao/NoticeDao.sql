SELECT * FROM NOTICES ORDER BY REGDATE DESC;
SELECT * FROM NOTICES;
SELECT * FROM NOTICEFILES;



SELECT MAX(CAST(CODE AS INT))+1 FROM NOTICEFILES;




--���� �������׿��� �ִ� ���� ������ �ڵ带 �������� ������ �ۼ��ؾ���. 
SELECT NVL(MAX(TO_NUMBER(CODE)),0) +1 FROM NOTICES; --Oracle
SELECT ISNULL(MAX(CAST(CODE AS INT)),0) FROM NOTICEFILES; --SQL Server



--20���� ���� ���ڵ带 ������ ������ �ۼ��Ͻÿ�. 
--1������, 2������ ������. 
SELECT * FROM NOTICES ORDER BY REGDATE DESC;
--ROWNUM�� �̿��� ������ ������ �ʿ�. 
SELECT * FROM (SELECT * FROM NOTICES ORDER BY REGDATE DESC) NOTICES WHERE ROWNUM BETWEEN 1 AND 20;

--SQL Server 
SELECT N.* FROM 
(
	SELECT (ROW_NUMBER() OVER (ORDER BY REGDATE DESC)) NUM, NOTICES.* FROM NOTICES
) N
WHERE N.NUM BETWEEN 11 AND 20;

--prevNotice(�츮�� nextNotice)
SELECT * FROM NOTICES ORDER BY REGDATE DESC;
SELECT * FROM NOTICES WHERE REGDATE > (SELECT REGDATE FROM NOTICES WHERE CODE = '240') ORDER BY REGDATE ASC;
--nextNotice(�츮�� prevNotice)
SELECT TOP 1 * FROM NOTICES 
WHERE REGDATE < (SELECT REGDATE FROM NOTICES WHERE CODE = '240') 
ORDER BY REGDATE DESC;
--hol.....(Ȧ0Ȧ)




--�Ʊ� ������ �� �������ϱ� �ϳ��� �Է�������.. INSERT�� ���� ���ְ� �� �Է��� ����. UPDATE�϶� WHERE�ΰ�... 
INSERT INTO NOTICES(CODE, TITLE, WRITER, REGDATE, CONTENT, HIT) 
VALUES('146', '����������������', 'Namita', SYSDATE, '���볻�볻�볻�볻���������', 3);

INSERT INTO MEMBERS(MID, PWD, NAME) VALUES('Nami', '1234', '������');
SELECT * FROM MEMBERS;

--UPDATE NOTICES SET TITLE = '' WHERE WRITER = ''; 

SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES ORDER BY REGDATE DESC) N) NOTICES WHERE NUM BETWEEN 11 AND 20;

--������ ���� �� �ڿ�, ���ڸ� ���̰��� ����. SQL Server������ ���ĵ� ���¿��� �ѹ��� �ο�� ��� �� �� �ִ�.
-- ���ĵ� ���¿��� �ο���� �ְ� ��Ī NUM���� �����ʹ޶�.
SELECT (ROW_NUMBER() OVER (ORDER BY REGDATE DESC)) NUM FROM NOTICES;
SELECT (ROW_NUMBER() OVER (ORDER BY REGDATE DESC)) NUM, NOTICES.* FROM NOTICES;
--NOTICES�� ��� �÷��� �Բ� ��ȸ 